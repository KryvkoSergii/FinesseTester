package ua.com.smiddle.core.util.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by srg on 20.09.16.
 */
public class Request {
    private String extension;
    private String loginId;
    private Action action;


    //Constructor
    public Request() {
    }


    //Getters and setters
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("extension='").append(extension).append('\'');
        sb.append(", loginId='").append(loginId).append('\'');
        sb.append(", action=").append(action);
        sb.append('}');
        return sb.toString();
    }
}
