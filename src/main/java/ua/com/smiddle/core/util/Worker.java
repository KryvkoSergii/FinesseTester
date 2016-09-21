package ua.com.smiddle.core.util;


import ua.com.smiddle.core.util.model.AgentStates;

import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by srg on 21.09.16.
 */
public class Worker extends SwingWorker {
    private AgentStates state;
    private String loginId;
    private String password;

    public Worker() {

    }

    public Worker(AgentStates state, String loginId, String password) {
        this.state = state;
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Object doInBackground() throws Exception {
        return null;
    }


}
