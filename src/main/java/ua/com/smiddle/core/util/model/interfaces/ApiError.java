package ua.com.smiddle.core.util.model.interfaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by srg on 26.09.16.
 */
@XmlRootElement(name = "ApiError")
public class ApiError {
    @XmlElement(name = "ErrorType")
    private String errorType;
    @XmlElement(name = "ErrorMessage")
    private String errorMessage;
    @XmlElement(name = "ErrorData")
    private String errorData;


    //Constructors
    public ApiError() {
    }

    public ApiError(String errorType, String errorMessage, String errorData) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.errorData = errorData;
    }


    //Getters and setters
    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorData() {
        return errorData;
    }

    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiError{");
        sb.append("errorType='").append(errorType).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", errorData='").append(errorData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
