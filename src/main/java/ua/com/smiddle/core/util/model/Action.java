package ua.com.smiddle.core.util.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by srg on 22.09.16.
 */
public enum Action {
    LOGIN("LOGIN"), LOGOUT("LOGOUT"), NOT_READY("NOT_READY"), READY("READY"),
    TALKING("TALKING"), WORK_READY("WORK_READY"), RESERVED("RESERVED"), RESERVED_OUTBOUND("RESERVED_OUTBOUND"),
    UNKNOWN("UNKNOWN"), HOLD("HOLD"), RESERVED_OUTBOUND_PREVIEW("RESERVED_OUTBOUND_PREVIEW"),
    CALL("CALL"), RESPONSE("RESPONSE");

    private final String value;

    private Action(final String description) {
        this.value = description;
    }

    @JsonValue
    final String value() {
        return this.value;
    }
}
