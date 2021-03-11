package infrastructure.bus.query;

import lombok.Getter;

@Getter
public class QueryHandlerNotFound extends RuntimeException {

    private final String query;

    public QueryHandlerNotFound(String query) {
        super("QUERY_HANDLER_NOT_FOUND");
        this.query = query;
    }
}
