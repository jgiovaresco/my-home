package my.home.bc.account.port.in.web;

import cqrs.command.CommandBus;
import cqrs.command.CommandResponse;
import java.security.Principal;
import my.home.bc.account.command.CreateAccountCommand;
import my.home.bc.account.model.Account;
import my.home.bc.account.port.in.web.model.AccountDto;
import my.home.bc.account.port.in.web.model.NewAccountDto;
import my.home.infrastructure.web.security.Auth0JwtAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
public class AccountResource {

    private final CommandBus commandBus;

    public AccountResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    public Mono<ResponseEntity<AccountDto>> registerNewAccount(
        UriComponentsBuilder uriBuilder,
        @RequestBody NewAccountDto newAccountDto,
        Mono<Principal> principal
    ) {
        return principal
            .map(p -> new Auth0JwtAuthenticationToken((JwtAuthenticationToken) p))
            .flatMap(
                token -> {
                    CreateAccountCommand command = CreateAccountCommand
                        .builder()
                        .email(token.getEmail())
                        .externalId(token.getSub())
                        .firstname(newAccountDto.getFirstName())
                        .lastname(newAccountDto.getLastName())
                        .build();

                    return commandBus
                        .<Account>send(command)
                        .map(CommandResponse::getResponse)
                        .map(
                            account ->
                                ResponseEntity
                                    .created(uriBuilder.replacePath("/api/accounts/{id}").build(account.getId()))
                                    .body(AccountDto.from(account))
                        );
                }
            );
    }
}
