package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Предназначен для отправки запросов по JSON из TPS(thrid-party-system) -> SmiddleFinesseConnector.
 * <br/><h3>Содержит следующие данные:</h3>
 * <br/>{@code loginId} - идентификатор агента в системе Cisco Finesse;
 * <br/>{@code extension} - номер аппарата агента;
 * <br/>{@code action} - стостояние агента / выполняемое действие агентом;
 * <br/>{@code password} - пароль агента в системе Cisco Finesse;
 * <br/>{@code dialogId} - идентификатор диалога в системе Cisco Finesse
 * <br/>{@code toAddress} - целевой номер аппарата при исходящем звонке
 *
 * @author Kryvko Sergii (ksa@smiddle.com.ua)
 * @project SmiddleFinesseConnector
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
    //идентификатор диалога
    private String dialogId;
    //целевой телефон при исходящем звонке
    private String toAddress;
    //URL отправки событий пользователю
    private String subscriptionURL;
    //формат получаемых сообщений json или xml
    private String messageFormat;


    //Constructor
    public Request() {
    }

    /**
     * Предназначен для отправки запросов по JSON из TPS(thrid-party-system) -> SmiddleFinesseConnector.
     *
     * @param loginId         идентификатор агента в системе Cisco Finesse
     * @param extension       номер аппарата агента
     * @param action          стостояние агента / выполняемое действие агентом
     * @param password        пароль агента в системе Cisco Finesse
     * @param dialogId        идентификатор диалога в системе Cisco Finesse
     * @param toAddress       целевой номер аппарата при исходящем звонке
     * @param subscriptionURL URL отправки событий пользователю
     */
    public Request(String loginId, String extension, Action action, String password, String dialogId, String toAddress,
                   String subscriptionURL, String messageFormat) {
        this.loginId = loginId;
        this.extension = extension;
        this.action = action;
        this.password = password;
        this.dialogId = dialogId;
        this.toAddress = toAddress;
        this.subscriptionURL = subscriptionURL;
        this.messageFormat = messageFormat;
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

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubscriptionURL() {
        return subscriptionURL;
    }

    public void setSubscriptionURL(String subscriptionURL) {
        this.subscriptionURL = subscriptionURL;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append(", action=").append(action);
        sb.append(", dialogId='").append(dialogId).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append(", subscriptionURL='").append(subscriptionURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
