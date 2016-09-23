package ua.com.smiddle.core.util.model;

/**
 * Created by srg on 22.09.16.
 */
public class Event extends Request {
    //содержит ид диалога, при выполении действия ("Action.ANSWER")
    private String dialogId;
    public Event() {
    }

    public Event(String loginId, Action state, String extension) {
        this.setLoginId(loginId);
        this.setAction(state);
        this.setExtension(extension);
    }

    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
