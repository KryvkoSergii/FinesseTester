package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Предназначен для передачи информации о диалоге по JSON.
 *
 * @author KSA
 */
@JsonIgnoreProperties
public class OutboundDialog {
    List<CallVariable> callVariables;
    private String id;
    private String fromAddress;
    private String toAddress;


    //Constructors
    public OutboundDialog() {
    }

    public OutboundDialog(String id, String fromAddress, String toAddress, List<CallVariable> callVariables) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.callVariables = callVariables;
    }


    //Methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public List<CallVariable> getCallVariables() {
        return callVariables;
    }

    public void setCallVariables(List<CallVariable> callVariables) {
        this.callVariables = callVariables;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OutboundDialog{");
        sb.append("id='").append(id).append('\'');
        sb.append(", fromAddress='").append(fromAddress).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append(", callVariables=").append(callVariables);
        sb.append('}');
        return sb.toString();
    }
}
