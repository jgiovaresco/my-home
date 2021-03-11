package cqrs.query;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final List<String> messages = new ArrayList<>();

    public ValidationException(List<String> messages) {
        this.messages.addAll(messages);
    }
}
