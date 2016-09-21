package ua.com.smiddle.core.util.model;

/**
 * Created by srg on 20.09.16.
 */
public class Header {
    private String message;
    private ResponseCode code;


    //Constructors
    public Header() {
    }

    public Header(ResponseCode code) {
        this.code = code;
    }

    public Header(String message, ResponseCode code) {
        this.message = message;
        this.code = code;
    }


    //Getters and settes
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Header{");
        sb.append("message='").append(message).append('\'');
        sb.append(", code=").append(code);
        sb.append('}');
        return sb.toString();
    }
}
