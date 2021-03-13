package my.home.infrastructure;

import ddd.IdGenerator;

public class FakeGenerator implements IdGenerator {

    private final String value;

    public FakeGenerator(String value) {
        this.value = value;
    }

    @Override
    public String generateId() {
        return value;
    }
}
