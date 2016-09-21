package ua.com.smiddle.core.util.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import ua.com.smiddle.core.util.model.Header;
import ua.com.smiddle.core.util.model.Request;
import ua.com.smiddle.core.util.model.ResponseCode;

import javax.servlet.http.HttpServletRequest;

@RestController("RESTController")
@RequestMapping("/api")
public class RESTController {

    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public Header login(@RequestBody Request request, HttpServletRequest HTTPrequest) {
        try {

            return new Header(ResponseCode.SUCCESSFUL);
        } catch (Exception e) {
//            logger.logAnyway("TemplateRESTController", "editCriteriaGroup: throws Exception=" + e.getMessage());
//            response.addHeader("RETURNED_MESSAGE", e.getMessage());
            e.printStackTrace();
            return new Header(e.getMessage(), ResponseCode.EXCEPTION);
        }
    }
}
