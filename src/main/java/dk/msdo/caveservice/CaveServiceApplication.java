package dk.msdo.caveservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.Arrays;

@SpringBootApplication
public class CaveServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(CaveServiceApplication.class);

    public static void main(String[] args) {

        /**
         * Run the application
         */
        ConfigurableEnvironment environment = new StandardEnvironment();
        SpringApplication springApplication = new SpringApplication(CaveServiceApplication.class);
        ApplicationContext ctx = springApplication.run(args);

         /**
         * Document loaded beans for a newbie :)
         */
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info("method=main, Bean: " + beanName);
        }
    }
}
