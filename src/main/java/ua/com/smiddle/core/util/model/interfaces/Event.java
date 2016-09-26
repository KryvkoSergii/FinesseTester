package ua.com.smiddle.core.util.model.interfaces;

/**
 * Предназначен для отправки сведений о событии SmiddleFinesseConnector -> TPS(thrid-party-system)
 * @author KSA
 */
public class Event {
    //индентификатор агента
    private String loginId;
    //содержит состояние агента
    private Action state;
    //содержит описание ошибки
    private ApiError apiError;
    //содержит информацию о диалоге
    private OutboundDialog dialog;


    //Constructors
    public Event() {
    }

    public Event(String loginId, Action state, ApiError apiError, OutboundDialog dialog) {
        this.loginId = loginId;
        this.state = state;
        this.apiError = apiError;
        this.dialog = dialog;
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

    public OutboundDialog getDialog() {
        return dialog;
    }

    public void setDialog(OutboundDialog dialog) {
        this.dialog = dialog;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", state=").append(state);
        sb.append(", apiError=").append(apiError);
        sb.append(", dialog=").append(dialog);
        sb.append('}');
        return sb.toString();
    }
}
