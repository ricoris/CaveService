package dk.msdo.caveservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class CaveServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(CaveServiceApplication.class);

    public static void main(String[] args) {
        /*
         * Run the application
         */
        SpringApplication springApplication = new SpringApplication(CaveServiceApplication.class);
        ApplicationContext ctx = springApplication.run(args);
         /*
         * Document loaded beans for a newbie :) - the list is only sent log if debug: true in application.yml
         */
         if (ctx.getEnvironment().getProperty("debug").equals("true")) {
             logger.info("********************* BEAN LIST *********************");
             String[] beanNames = ctx.getBeanDefinitionNames();
             Arrays.sort(beanNames);
             for (String beanName : beanNames) {
                 logger.info("method=main, Bean: " + beanName);
             }
         }
    }
}
