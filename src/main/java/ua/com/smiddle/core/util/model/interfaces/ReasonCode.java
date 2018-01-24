package ua.com.smiddle.core.util.model.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ksa on 1/22/18.
 * @project SmiddleFinesseConnector
 */
@XmlRootElement(name = "ReasonCode")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReasonCode {
    private final static String urlPref = "/finesse/api/ReasonCode/";
    @XmlElement(name = "uri")
    private String uri;
    @XmlElement(name = "category")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Action category;
    @XmlElement(name = "code")
    private String code;
    @XmlElement(name = "label")
    private String label;
    @XmlElement(name = "forAll")
    private boolean forAll;
    private String id;


    //Constructors
    public ReasonCode() {
    }


    //Getters and setters
    public String getUri() {
        return uri;
    }

    @JsonSetter
    public void setUri(String uri) {
        this.uri = uri;
        this.id = calculateId();
    }

    public Action getCategory() {
        return category;
    }

    public void setCategory(Action category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isForAll() {
        return forAll;
    }

    public void setForAll(boolean forAll) {
        this.forAll = forAll;
    }

    public String getId() {
        return this.id;
    }

    private String calculateId() {
        return (uri != null && uri.contains(urlPref)) ? uri.substring(uri.indexOf(urlPref) + urlPref.length(), uri.length()) : null;
    }
}
