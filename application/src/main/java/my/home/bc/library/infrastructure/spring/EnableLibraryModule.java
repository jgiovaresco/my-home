package my.home.bc.library.infrastructure.spring;

import java.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
@Import(LibraryModuleConfiguration.class)
@Configuration
public @interface EnableLibraryModule {
}
