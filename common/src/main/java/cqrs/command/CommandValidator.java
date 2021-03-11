package cqrs.command;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandValidator implements CommandMiddleware {

    private final Validator validator;

    @Override
    public void beforeExecution(Command command) {
        validate(command);
    }

    public void validate(Command command) {
        Set<ConstraintViolation<Command>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ValidationException(enMessages(violations));
        }
    }

    private List<String> enMessages(Set<ConstraintViolation<Command>> violations) {
        return violations.stream().map(v -> v.getPropertyPath() + " " + v.getMessage()).collect(toList());
    }
}
