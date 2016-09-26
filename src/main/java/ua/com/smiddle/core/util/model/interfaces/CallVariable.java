package ua.com.smiddle.core.util.model.interfaces;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CallVariable")
public class CallVariable {
    private String name;
    private String value;


    //Constructors
    public CallVariable() {
    }


    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallVariable{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
