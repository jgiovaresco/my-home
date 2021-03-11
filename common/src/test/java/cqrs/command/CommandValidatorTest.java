package cqrs.command;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class CommandValidatorTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final CommandValidator validator = new CommandValidator(factory.getValidator());

    @Test
    void validates_a_command() {
        Assertions
            .assertThatThrownBy(() -> validator.validate(new FakeCommand("")))
            .isInstanceOf(ValidationException.class);
    }

    @Test
    void gives_error_cause() {
        Assertions
            .assertThatThrownBy(() -> validator.validate(new FakeCommand("")))
            .isInstanceOf(ValidationException.class)
            .extracting("messages", InstanceOfAssertFactories.ITERABLE)
            .contains("name must not be empty");
    }

    @Test
    void calls_validation_before_executing_command() {
        Assertions
            .assertThatThrownBy(() -> validator.beforeExecution(new FakeCommand("")))
            .isInstanceOf(ValidationException.class);
    }

    private static class FakeCommand implements Command {

        @NotEmpty
        String name;

        public FakeCommand(@NotEmpty String name) {
            this.name = name;
        }
    }
}
