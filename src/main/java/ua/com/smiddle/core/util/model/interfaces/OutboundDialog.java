package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Предназначен для передачи информации о диалоге SmiddleFinesseConnector -> TPS(thrid-party-system) по JSON.
 * Содержит следующие данные: {@code id} - идентификатор диалога в системе Cisco Finesse; {@code fromAddress} - номер аппарата автора звонка;
 * {@code toAddress} - номер аппарата получателя звонка; {@code callVariables} - переменные звонка.
 *
 * @author Kryvko Sergii (ksa@smiddle.com.ua)
 * @project SmiddleFinesseConnector
 */
@JsonIgnoreProperties
public class OutboundDialog {
    List<CallVariable> callVariables;
    private String id;
    private String fromAddress;
    private String toAddress;
    private String DNIS;
    private String callType;


    //Constructors
    public OutboundDialog() {
    }

    /**
     * Предназначен для передачи информации о диалоге SmiddleFinesseConnector -> TPS(thrid-party-system) по JSON.
     *
     * @param id            идентификатор диалога CiscoFinesse
     * @param fromAddress   номер аппарата автора звонка
     * @param toAddress     номер аппарата получателя звонка
     * @param callVariables переменные звонка
     * @param DNIS          номер, который набрал клиент
     * @param callType      тип звонка
     */
    public OutboundDialog(String id, String fromAddress, String toAddress, List<CallVariable> callVariables, String DNIS, String callType) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.callVariables = callVariables;
        this.DNIS = DNIS;
        this.callType = callType;
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

    public String getDNIS() {
        return DNIS;
    }

    public void setDNIS(String DNIS) {
        this.DNIS = DNIS;
    }

    public List<CallVariable> getCallVariables() {
        return callVariables;
    }

    public void setCallVariables(List<CallVariable> callVariables) {
        this.callVariables = callVariables;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OutboundDialog{");
        sb.append("id='").append(id).append('\'');
        sb.append(", fromAddress='").append(fromAddress).append('\'');
        sb.append(", toAddress='").append(toAddress).append('\'');
        sb.append(", callVariables=").append(callVariables);
        sb.append(", DNIS=").append(DNIS);
        sb.append(", callType=").append(callType);
        sb.append('}');
        return sb.toString();
    }
}
