package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Предназначен для отправки запросов по JSON из TPS(thrid-party-system) -> SmiddleFinesseConnector.
 *
 * @author KSA
 */
public class Request {
    //Идентификтор агента
    private String loginId;
    //Номер телефонного аппарата
    private String extension;
    //состояние агента
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Action action;
    //пароль агента
    private String password;
    //
    private String dialogId;


    //Constructor
    public Request() {
    }

    public Request(String loginId, String extension, Action action, String password, String dialogId) {
        this.loginId = loginId;
        this.extension = extension;
        this.action = action;
        this.password = password;
        this.dialogId = dialogId;
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

    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", action=").append(action);
        sb.append(", password='").append(password).append('\'');
        sb.append(", dialogId='").append(dialogId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
