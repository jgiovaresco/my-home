package my.home;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import my.home.bc.account.infrastructure.spring.EnableAccountModule;
import my.home.bc.library.infrastructure.spring.EnableLibraryModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAccountModule
@EnableLibraryModule
public class MyHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyHomeApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${info.app.version}") String appVersion) {
        return new OpenAPI()
        .info(
                new Info()
                    .title("My Own Library API")
                    .version(appVersion)
                    .description("This is the My Home™️ API documentation.")
            );
    }
}
