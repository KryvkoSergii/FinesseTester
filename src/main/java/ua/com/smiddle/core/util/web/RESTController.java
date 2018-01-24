package ua.com.smiddle.core.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.smiddle.core.util.model.interfaces.Action;
import ua.com.smiddle.core.util.model.interfaces.Event;
import ua.com.smiddle.core.util.util.FinesseForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController("RESTController")
@RequestMapping("/api")
public class RESTController {
    @Autowired
    @Qualifier("FinesseForm")
    private FinesseForm finesseForm;


    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public void event(@RequestBody Event request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        try {
            finesseForm.addLog("got Event " + request.toString());
            if (request.getApiError() != null) {
                finesseForm.showError(request.getApiError());
            } else if (request.getState() != null) {
                finesseForm.changeAgentState(request.getState());
                updateReasonCodes(request);
            } else if (request.getDialogs() != null && request.getDialogs().length > 0) {
                finesseForm.setDialog(request.getDialogs()[0]);
            }
            httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void updateReasonCodes(@RequestBody Event request) {
        if (request.getAgent() != null &&
                request.getAgent().getReasonCode() != null &&
                request.getState() == Action.NOT_READY) {
            finesseForm.update(Action.LOGOUT, request.getAgent().getReasonCode());
        } else if (request.getAgent() != null &&
                request.getAgent().getReasonCode() != null &&
                request.getState() == Action.READY) {
            finesseForm.update(Action.NOT_READY, request.getAgent().getReasonCode());
        }
    }
}
