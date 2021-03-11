package cqrs.query;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryValidator implements QueryMiddleware {

    private final Validator validator;

    @Override
    public void beforeExecution(Query query) {
        validate(query);
    }

    public void validate(Query query) {
        Set<ConstraintViolation<Query>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ValidationException(enMessages(violations));
        }
    }

    private List<String> enMessages(Set<ConstraintViolation<Query>> violations) {
        return violations.stream().map(v -> v.getPropertyPath() + " " + v.getMessage()).collect(toList());
    }
}
