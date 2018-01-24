package ua.com.smiddle.core.util.model.interfaces;

import java.util.Arrays;

/**
 * Предназначен для отправки сведений о событии SmiddleFinesseConnector -> TPS(thrid-party-system).
 * <br/><h3>Содержит следующие данные:</h3> <br/>{@code loginId} - идентификатор агента в системе Cisco Finesse; <br/>{@code state} - стостоние агента;
 * <br/>{@code apiError} - информация о ошибке выполения запроса к Cisco Finesse; <br/>{@code dialogs} - информация о диалоге из Cisco Finesse.
 *
 * @author Kryvko Sergii (ksa@smiddle.com.ua)
 * @project SmiddleFinesseConnector
 */
public class Event {
    //индентификатор агента
    private String loginId;
    //содержит состояние агента
    private Action state;
    //содержит описание ошибки
    private ApiError apiError;
    //содержит информацию о диалогах
    private OutboundDialog[] dialogs;
    private Agent agent;


    //Constructors
    public Event() {
    }

    /**
     * Предназначен для отправки сведений о событии SmiddleFinesseConnector -> TPS(thrid-party-system).
     *
     * @param loginId  идентификатор агента в системе Cisco Finesse
     * @param state    стостоние агента
     * @param apiError информация о ошибке выполения запроса к Cisco Finesse
     * @param dialog   информация о диалоге из Cisco Finesse
     */
    public Event(String loginId, Action state, ApiError apiError, OutboundDialog[] dialog, Agent agent) {
        this.loginId = loginId;
        this.state = state;
        this.apiError = apiError;
        this.dialogs = dialog;
        this.agent = agent;
    }


    //Getters and setters
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Action getState() {
        return state;
    }

    public void setState(Action state) {
        this.state = state;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public OutboundDialog[] getDialogs() {
        return dialogs;
    }

    public void setDialogs(OutboundDialog[] dialogs) {
        this.dialogs = dialogs;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", state=").append(state);
        sb.append(", apiError=").append(apiError);
        sb.append(", dialogs=").append(Arrays.toString(dialogs));
        sb.append(", agent=").append(agent);
        sb.append('}');
        return sb.toString();
    }
}