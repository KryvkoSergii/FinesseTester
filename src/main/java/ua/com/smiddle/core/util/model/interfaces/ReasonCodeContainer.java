package ua.com.smiddle.core.util.model.interfaces;

import java.util.List;

/**
 * @author ksa on 1/24/18.
 * @project FinesseTester
 */
public interface ReasonCodeContainer {
    List<ReasonCode> getNotReadyReasonCode();

    List<ReasonCode> getLogoutReasonCode();

    void update(Action action, List<ReasonCode> codes);
}
