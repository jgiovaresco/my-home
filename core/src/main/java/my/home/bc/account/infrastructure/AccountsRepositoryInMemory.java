package my.home.bc.account.infrastructure;

import java.util.ArrayList;
import java.util.List;
import my.home.bc.account.model.Account;
import my.home.bc.account.repository.AccountRepository;
import reactor.core.publisher.Mono;

public class AccountsRepositoryInMemory implements AccountRepository {

    private final List<Account> storage = new ArrayList<>();

    public void reset() {
        storage.clear();
    }

    @Override
    public Mono<Boolean> add(Account account) {
        return Mono.just(storage.add(account));
    }

    @Override
    public Mono<Account> findByEmail(String email) {
        var account = storage.stream().filter(a -> a.getEmail().equals(email)).findFirst();
        return Mono.justOrEmpty(account);
    }
}
