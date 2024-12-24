package co.com.sofkau;


import co.com.sofkau.gateway.IAccountRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetAccountByAccountNumberUseCase {
    private final IAccountRepository accountRepository;

    public GetAccountByAccountNumberUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Mono<Account> apply(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).switchIfEmpty(Mono.empty());
    }
}
