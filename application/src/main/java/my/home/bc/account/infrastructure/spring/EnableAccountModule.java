package my.home.bc.account.infrastructure.spring;

import java.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
@Import(AccountModuleConfiguration.class)
@Configuration
public @interface EnableAccountModule {
}
