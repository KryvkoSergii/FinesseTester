import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.smiddle.core.util.util.FinesseForm;


/**
 * Created by srg on 14.09.16.
 */
//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "ua.com.smiddle.core")
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }

}
