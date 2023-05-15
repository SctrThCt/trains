package stc.trains;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class TrainsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainsApplication.class, args);
    }

}
