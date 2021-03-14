package my.home.infrastructure.web;

import com.github.javafaker.Faker;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AbstractIntegrationTest.Config.class)
public class AbstractIntegrationTest {

    @MockBean
    public ReactiveJwtDecoder jwtDecoder;

    @TestConfiguration
    static class Config {

        @Bean
        public Faker faker() {
            return new Faker();
        }
    }
}
