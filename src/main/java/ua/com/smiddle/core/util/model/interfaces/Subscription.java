package ua.com.smiddle.core.util.model.interfaces;

/**
 * @author Kryvko Sergii (ksa@smiddle.com.ua) on 28.09.16.
 * @project SmiddleFinesseConnector
 */
public class Subscription {
    //URL отправки событий сервету TPS(thrid-party-system)
    private String subscriptionURL;


    //Constructors
    public Subscription() {
    }

    public Subscription(String subscriptionURL) {
        this.subscriptionURL = subscriptionURL;
    }


    //Getters and settres
    public String getSubscriptionURL() {
        return subscriptionURL;
    }

    public void setSubscriptionURL(String subscriptionURL) {
        this.subscriptionURL = subscriptionURL;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("subscriptionURL='").append(subscriptionURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
