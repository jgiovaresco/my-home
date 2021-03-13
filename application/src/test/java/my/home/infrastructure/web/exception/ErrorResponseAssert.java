package my.home.infrastructure.web.exception;

import java.util.Arrays;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ErrorResponseAssert extends AbstractAssert<ErrorResponseAssert, ErrorResponse> {

    public ErrorResponseAssert(ErrorResponse errorResponse) {
        super(errorResponse, ErrorResponseAssert.class);
    }

    public ErrorResponseAssert hasStatus(int status) {
        isNotNull();
        Assertions
            .assertThat(actual.getStatus())
            .overridingErrorMessage("Expected error's status to be <%s> but was <%s>", status, actual.getStatus())
            .isEqualTo(status);

        return this;
    }

    public ErrorResponseAssert hasMessage(String message) {
        isNotNull();
        Assertions
            .assertThat(actual.getMessage())
            .overridingErrorMessage("Expected error's message to be <%s> but was <%s>", message, actual.getMessage())
            .isEqualTo(message);

        return this;
    }

    public ErrorResponseAssert hasErrors(String... errors) {
        isNotNull();
        Assertions
            .assertThat(actual.getErrors())
            .overridingErrorMessage(
                "Expected error's errors to contains <%s> but was <%s>",
                Arrays.asList(errors),
                actual.getErrors()
            )
            .containsAnyOf(errors);

        return this;
    }
}
