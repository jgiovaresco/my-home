package my.home.bc.account.repository;

import my.home.bc.account.model.Account;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<Boolean> add(Account account);

    Mono<Account> findByEmail(String email);
}
