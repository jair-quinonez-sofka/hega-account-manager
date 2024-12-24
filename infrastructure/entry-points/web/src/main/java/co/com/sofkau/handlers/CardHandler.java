package co.com.sofkau.handlers;

import co.com.sofkau.CreateCardUseCase;
import co.com.sofkau.data.CardDTO;
import co.com.sofkau.mapper.CardDTOMapper;
import co.com.sofkau.mapper.CardMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CardHandler {
    private final CreateCardUseCase createCardUseCase;


    public CardHandler(CreateCardUseCase createCardUseCase) {
        this.createCardUseCase = createCardUseCase;
    }

    public Mono<CardDTO> createCard(CardDTO cardDTO) {
        return createCardUseCase.apply(CardDTOMapper.toCard(cardDTO)).map(CardDTOMapper::toCardDTO);
    }
}
