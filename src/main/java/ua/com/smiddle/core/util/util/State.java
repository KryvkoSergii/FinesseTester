package ua.com.smiddle.core.util.util;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import ua.com.smiddle.core.util.model.interfaces.Action;
import ua.com.smiddle.core.util.model.interfaces.Request;


import javax.annotation.PostConstruct;

/**
 * Created by srg on 21.09.16.
 */
@Component("State")
@Description("Contains logged client state and credentials")
public class State extends Request {
    private String password;
    private String token;


    //Constructors
    public State() {
        this.setAction(Action.UNKNOWN);
    }


    //Getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    //Method
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("State{");
        sb.append(super.toString()).append(",");
        sb.append("password='").append(password).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @PostConstruct
    private void init() {
        this.setAction(Action.UNKNOWN);
    }
}
