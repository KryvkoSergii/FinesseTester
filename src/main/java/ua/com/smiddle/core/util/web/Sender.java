package ua.com.smiddle.core.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ua.com.smiddle.core.util.model.interfaces.Action;
import ua.com.smiddle.core.util.model.interfaces.Request;
import ua.com.smiddle.core.util.model.interfaces.Subscription;
import ua.com.smiddle.core.util.util.FinesseForm;
import ua.com.smiddle.core.util.util.JacksonUtil;
import ua.com.smiddle.core.util.util.State;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by srg on 21.09.16.
 */
@Component("Sender")
@Scope("singleton")
public class Sender {
    @Autowired
    private Environment environment;
    @Autowired
    @Qualifier("State")
    private State state;
    @Autowired
    @Qualifier("FinesseForm")
    private FinesseForm finesseForm;
    private BufferedWriter bw;

    public void login() throws Exception {
        Request r = new Request(state.getLoginId(), state.getExtension(), Action.LOGIN, state.getPassword(), null, null, buildSubscriptionURL());
        makeRequest("/action", r);
    }

    public void change_state(Object request) throws Exception {
        Request r = new Request(state.getLoginId(), state.getExtension(), (Action) request, state.getPassword(), null, null, null);
        makeRequest("/action", r);
    }

    public void sendAction(Object request, String dialogId) throws Exception {
        Request r = new Request(state.getLoginId(), state.getExtension(), (Action) request, state.getPassword(), dialogId, null, null);
        makeRequest("/action", r);
    }

    public void makeCall(Object request, String toAddress) throws Exception {
        Request r = new Request(state.getLoginId(), state.getExtension(), (Action) request, state.getPassword(), null, toAddress, null);
        makeRequest("/action", r);
    }

    public void makeConsultCall(Object request, String toAddress, String dialogId) throws Exception {
        Request r = new Request(state.getLoginId(), state.getExtension(), (Action) request, state.getPassword(), dialogId, toAddress, null);
        makeRequest("/action", r);
    }

    public void sendSubscription(String action, String authToken) throws Exception {
        Subscription subscription = new Subscription(buildSubscriptionURL(), authToken);
        if (action.equals("subscribe")) {
            makeRequest("/subscribe", subscription);
            finesseForm.addLog("Subscription: " + subscription.toString());
        } else if (action.equals("unsubscribe")) {
            makeRequest("/unsubscribe", subscription);
            finesseForm.addLog("Unsubscription: " + subscription.toString());
        } else {
            finesseForm.addLog("Unknown subscription state");
        }
    }

    private void makeRequest(String actionURL, Object request) throws Exception {
        URL url = new URL(buildURL(actionURL));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        String json = JacksonUtil.objectToJson(request);
        finesseForm.addLog(json + " to " + url);

        bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Charset.forName("UTF-8")));
        bw.write(json);
        bw.flush();

        if (connection.getResponseCode() != 202) {
            throw new Exception("Response: code=" + connection.getResponseCode() + ", message=" + connection.getResponseMessage());
        }
        connection.disconnect();
    }

    private String buildURL(String actionURL) {
        String url = "http://".concat(environment.getProperty("connection.connection.ip")
                .concat(":").concat(environment.getProperty("connection.connection.port"))
                .concat("/finesse")
                .concat("/connector").concat(actionURL));
        return url;
    }

    private void addAuth(HttpURLConnection connection, String loginId, String pass) {
        if (state.getToken() != null) {
            connection.setRequestProperty("Authorization", state.getToken());
        } else {
            byte[] inBytes = (loginId.concat(":").concat(pass)).getBytes(Charset.forName("UTF-8"));
            String token = "Basic " + new String(Base64.getEncoder().encode(inBytes), Charset.forName("UTF-8"));
            connection.setRequestProperty("Authorization", token);
            state.setToken(token);
        }
    }

    private String buildSubscriptionURL() {
        String url = "http://".concat(state.getIP())
                .concat(":").concat(environment.getProperty("server.port"))
                .concat("/api/event");
        return url;
    }
}
