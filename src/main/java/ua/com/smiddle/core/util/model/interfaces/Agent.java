package ua.com.smiddle.core.util.model.interfaces;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ksa on 1/22/18.
 * @project SmiddleFinesseConnector
 */
public class Agent {
    private String loginId;
    private List<ReasonCode> reasonCode;


    //Constructor
    public Agent() {
    }

    public Agent(String loginId, List<ReasonCode> reasonCode) {
        this.loginId = loginId;
        this.reasonCode = reasonCode;
    }


    //Getters and setters
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<ReasonCode> getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(List<ReasonCode> reasonCode) {
        this.reasonCode = reasonCode;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Agent{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", reasonCode=").append(reasonCode != null ? reasonCode.stream().map(e->e.toString()).collect(Collectors.joining(",","[","]")): null);
        sb.append('}');
        return sb.toString();
    }
}
