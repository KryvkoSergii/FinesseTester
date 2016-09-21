package ua.com.smiddle.core.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ua.com.smiddle.core.util.model.Action;
import ua.com.smiddle.core.util.model.Request;
import ua.com.smiddle.core.util.util.State;

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

    public void login() throws Exception {
        Request r = new Request();
        r.setLoginId(state.getLoginId());
        r.setExtension(state.getExtension());
        r.setAction(Action.LOGIN);
        makeRequest(state.getLoginId(), state.getPassword(), "/login", r);
    }

    public void change_state(Object request) throws Exception {
        makeRequest(state.getLoginId(), state.getPassword(), "/change_state", request);
    }

    private void makeRequest(String loginId, String password, String actionURL, Object request) throws Exception {
        URL url = new URL(buildURL(actionURL));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        addAuth(connection, loginId, password);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();


        if (connection.getResponseCode() != 200) {
            throw new Exception("Respose code " + connection.getResponseCode());
        }
        connection.disconnect();
    }

    private String buildURL(String actionURL) {
        String url = "http://".concat(environment.getProperty("connection.connection.ip")
                .concat(":").concat(environment.getProperty("connection.connection.port"))
                .concat("/connector").concat(actionURL));
        return url;
    }

    private void addAuth(HttpURLConnection connection, String loginId, String pass) {
        if (state.getToken() != null) {
            connection.setRequestProperty("Authorization", state.getToken());
        } else {
            byte[] inBytes = (loginId.concat(":").concat(pass)).getBytes(Charset.forName("UTF-8"));
            String token = "Basic " + Base64.getEncoder().encode(inBytes);
            connection.setRequestProperty("Authorization", token);
            state.setToken(token);
        }
    }
}
