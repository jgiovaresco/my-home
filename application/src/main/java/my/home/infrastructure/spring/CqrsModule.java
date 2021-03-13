package my.home.infrastructure.spring;

import static java.util.Collections.emptySet;

import cqrs.command.CommandBus;
import cqrs.command.CommandHandler;
import cqrs.command.CommandMiddleware;
import cqrs.command.CommandValidator;
import cqrs.event.EventBus;
import cqrs.event.EventHandler;
import cqrs.query.QueryBus;
import cqrs.query.QueryHandler;
import cqrs.query.QueryMiddleware;
import infrastructure.bus.command.AsynchronousCommandBus;
import infrastructure.bus.event.AsynchronousEventBus;
import infrastructure.bus.query.AsynchronousQueryBus;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CqrsModule {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @SuppressWarnings("rawtypes")
    public CommandBus commandBus(ApplicationContext context, Set<CommandHandler> commandHandlers) {
        var commandValidator = context.getBean(CommandValidator.class);
        var eventBus = context.getBean(EventBus.class);

        var middlewares = new LinkedHashSet<CommandMiddleware>();
        middlewares.add(commandValidator);

        return new AsynchronousCommandBus(middlewares, commandHandlers, eventBus);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public QueryBus queryBus(Set<QueryMiddleware> queryMiddlewares, Set<QueryHandler> queryHandlers) {
        return new AsynchronousQueryBus(queryMiddlewares, queryHandlers);
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public AsynchronousEventBus eventBus(Set<EventHandler> eventHandlers) {
        return new AsynchronousEventBus(emptySet(), eventHandlers);
    }

    @Bean
    public CommandValidator commandValidator(Validator validator) {
        return new CommandValidator(validator);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
