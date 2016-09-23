package ua.com.smiddle.core.util.model;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by srg on 20.09.16.
 */
public class Request {
    private String extension;
    private String loginId;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Action action;
    private String password;
    //cодержит целевой номер телефона при выполении действия (позвонить)
    private String targetAddress;
    //содержит ид диалога, при выполении действия ("Action.ANSWER")
    private String dialogId;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("extension='").append(extension).append('\'');
        sb.append(", loginId='").append(loginId).append('\'');
        sb.append(", action=").append(action);
        sb.append(", targetAddress=").append(targetAddress);
        sb.append('}');
        return sb.toString();
    }
}
