package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Описывает сотояния агентов в UCCX/UCCE, содержит действия агентов.
 *
 * @author Kryvko Sergii (ksa@smiddle.com.ua)
 * @project SmiddleFinesseConnector
 */
public enum Action {
    //AGENT STATE UCCX
    LOGIN("LOGIN"), LOGOUT("LOGOUT"), NOT_READY("NOT_READY"), READY("READY"),
    TALKING("TALKING"), RESERVED_OUTBOUND_PREVIEW("RESERVED_OUTBOUND_PREVIEW"),
    UNKNOWN("UNKNOWN"),
    //AGENT STATE UCCE (ADDITIONAL TO UCCX)
    RESERVED("RESERVED"), WORK_READY("WORK_READY"), HOLD("HOLD"), RESERVED_OUTBOUND("RESERVED_OUTBOUND"), WORK("WORK"),
    //REQUESTED ACTIONS
    ANSWER("ANSWER"), MAKE_CALL("MAKE_CALL"), DROP("DROP"), RETRIEVE("RETRIEVE"), CONSULT_CALL("CONSULT_CALL"), TRANSFER("TRANSFER"),
    TRANSFER_SST("TRANSFER_SST"), UPDATE_CALL_DATA("UPDATE_CALL_DATA"), SEND_DTMF("SEND_DTMF"), CONFERENCE("CONFERENCE"),
    SILENT_MONITOR("SILENT_MONITOR"), BARGE_CALL("BARGE_CALL"), PARTICIPANT_DROP("PARTICIPANT_DROP"),
    START_RECORDING("START_RECORDING"), UPDATE_SCHEDULED_CALLBACK("UPDATE_SCHEDULED_CALLBACK"),
    CANCEL_SCHEDULED_CALLBACK("CANCEL_SCHEDULED_CALLBACK"),
    //ADDITIONAL States
    ALERTING("ALERTING");

    private final String value;

    private Action(final String description) {
        this.value = description;
    }

    @JsonValue
    final String value() {
        return this.value;
    }
}
