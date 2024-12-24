package co.com.sofkau.routes;

import co.com.sofkau.data.AccountDTO;
import co.com.sofkau.handlers.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AccountRouter {

    private final AccountHandler accountHandler;

    public AccountRouter(AccountHandler accountHandler) {
        this.accountHandler = accountHandler;
    }



    @Bean
    public RouterFunction<ServerResponse> accountRoutes() {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/v1/account"),this::getAllAccounts)
                .andRoute(RequestPredicates.POST("/api/v1/account/create").and(accept(MediaType.APPLICATION_JSON)), this::createAccount);
    }

    public Mono<ServerResponse> getAllAccounts(ServerRequest request) {

        return accountHandler.getAllAccounts()
                .collectList()
                .flatMap(elements ->
                        elements.isEmpty() ?
                                ServerResponse.status(HttpStatus.NOT_FOUND).build()
                                : ServerResponse.ok().body(Flux.fromIterable(elements), AccountDTO.class)

                );
    }

    public Mono<ServerResponse> createAccount(ServerRequest request) {

        return request.bodyToMono(AccountDTO.class)
                .flatMap(accountHandler::createAccount)
                .flatMap(accountDTO -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(accountDTO) );
    }

}
