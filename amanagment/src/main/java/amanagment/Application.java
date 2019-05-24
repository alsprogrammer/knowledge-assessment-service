package amanagment;

import io.micronaut.runtime.Micronaut;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("The application has been started");
        Micronaut.run(Application.class);
    }
}