package ua.com.smiddle.core.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import ua.com.smiddle.core.util.model.Header;
import ua.com.smiddle.core.util.model.Request;
import ua.com.smiddle.core.util.model.ResponseCode;
import ua.com.smiddle.core.util.util.FinesseForm;

import javax.servlet.http.HttpServletRequest;

@RestController("RESTController")
@RequestMapping("/api")
public class RESTController {
    @Autowired
    @Qualifier("FinesseForm")
    private FinesseForm finesseForm;

    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public Header event(@RequestBody Request request, HttpServletRequest HTTPrequest) {
        try {
            finesseForm.addLog("got Event " + request.toString());
            return new Header(ResponseCode.SUCCESSFUL);
        } catch (Exception e) {
            e.printStackTrace();
            return new Header(e.getMessage(), ResponseCode.EXCEPTION);
        }
    }
}
