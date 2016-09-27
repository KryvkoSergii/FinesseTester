package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Описывает сотояния агентов в UCCX/UCCE, содержит действия агентов.
 *
 * @author Kryvko Sergii
 */
public enum Action {
    //AGENT STATE UCCX
    LOGIN("LOGIN"), LOGOUT("LOGOUT"), NOT_READY("NOT_READY"), READY("READY"),
    TALKING("TALKING"), RESERVED_OUTBOUND_PREVIEW("RESERVED_OUTBOUND_PREVIEW"),
    UNKNOWN("UNKNOWN"),
    //AGENT STATE UCCE (ADDITIONAL TO UCCX)
    RESERVED("RESERVED"), WORK_READY("WORK_READY"), HOLD("HOLD"), RESERVED_OUTBOUND("RESERVED_OUTBOUND"), WORK("WORK"),
    //REQUESTED ACTIONS
    ANSWER("ANSWER"), MAKE_CALL("MAKE_CALL"), DROP("DROP"), RETRIEVE("RETRIEVE"),
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
