package co.com.sofkau.routes;

import co.com.sofkau.data.AccountDTO;
import co.com.sofkau.data.CardDTO;
import co.com.sofkau.handlers.CardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CardRouter {
    private final CardHandler cardHandler;


    public CardRouter(CardHandler cardHandler) {
        this.cardHandler = cardHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> cardRoutes() {
        return RouterFunctions
                .route(RequestPredicates.POST("/api/v1/card/create").and(accept(MediaType.APPLICATION_JSON)), this::createCard);
    }
    public Mono<ServerResponse> createCard(ServerRequest request) {

        return request.bodyToMono(CardDTO.class)
                .flatMap(cardHandler::createCard)
                .flatMap(card -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(card) );
    }
}
