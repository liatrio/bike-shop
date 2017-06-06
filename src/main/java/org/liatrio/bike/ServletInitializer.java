package org.liatrio.bike;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by bstein on 6/5/17.
 */
@SpringBootApplication(exclude = MessageSourceAutoConfiguration.class)
public class ServletInitializer extends SpringBootServletInitializer {

   /*public static void main(String[] args) {
      SpringApplication.run(ServletInitializer.class, args);
   }*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServletInitializer.class);
    }
}

@RestController
class RESTController {

    @RequestMapping("/")
    public String hello() {
        return "<H1>Hello World!</H1>";
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String getAllBikes() {
        return "<H1>There should be bikes here</H1>";
    }
}
