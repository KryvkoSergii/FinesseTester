package ua.com.smiddle.core.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import ua.com.smiddle.core.util.model.Header;
import ua.com.smiddle.core.util.model.ResponseCode;
import ua.com.smiddle.core.util.model.interfaces.Event;
import ua.com.smiddle.core.util.model.interfaces.OutboundDialog;
import ua.com.smiddle.core.util.util.FinesseForm;
import ua.com.smiddle.core.util.util.State;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

@RestController("RESTController")
@RequestMapping("/api")
public class RESTController {
    @Autowired
    @Qualifier("FinesseForm")
    private FinesseForm finesseForm;


    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public void event(@RequestBody Event request, HttpServletRequest HTTPrequest, HttpServletResponse HTTPresponce) throws IOException {
        try {
            finesseForm.addLog("got Event " + request.toString());
            if (request.getApiError() != null) {
                finesseForm.showError(request.getApiError());
            } else if (request.getState() != null) {
                finesseForm.changePropertyTo(request.getState());
            } else if (request.getDialog()!=null){
                finesseForm.setDialod(request.getDialog());
            }
            HTTPresponce.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (Exception e) {
            HTTPresponce.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
