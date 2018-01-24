package ua.com.smiddle.core.util.model.interfaces;

/**
 * @author Kryvko Sergii (ksa@smiddle.com.ua) on 28.09.16.
 * @project SmiddleFinesseConnector
 */
public class Subscription {
    //URL отправки событий сервету TPS(thrid-party-system)
    private String subscriptionURL;
    //содержит зашифрованный Base64 adminLogin:adminPassword
    private String authToken;
    //формат получаемых сообщений json или xml
    private String messageFormat;


    //Constructors
    public Subscription() {
    }

    public Subscription(String subscriptionURL) {
        this.subscriptionURL = subscriptionURL;
    }

    public Subscription(String subscriptionURL, String authToken) {
        this.subscriptionURL = subscriptionURL;
        this.authToken = authToken;
    }

    public Subscription(String subscriptionURL, String authToken, String messageFormat) {
        this.subscriptionURL = subscriptionURL;
        this.authToken = authToken;
        this.messageFormat = messageFormat;
    }


    //Getters and settres
    public String getSubscriptionURL() {
        return subscriptionURL;
    }

    public void setSubscriptionURL(String subscriptionURL) {
        this.subscriptionURL = subscriptionURL;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("subscriptionURL='").append(subscriptionURL).append('\'');
        sb.append(", authToken='").append(authToken).append('\'');
        sb.append(", messageFormat='").append(messageFormat).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
