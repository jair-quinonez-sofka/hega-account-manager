package co.com.sofkau.mapper;

import co.com.sofkau.Account;
import co.com.sofkau.data.AccountDTO;

public class AccountDTOMapper {
    public static Account toAccount(AccountDTO accountResponseDTO) {
        return new Account(null,accountResponseDTO.getAccountNumber(), accountResponseDTO.getAccountBalance(),
                accountResponseDTO.getAccountOwner(), accountResponseDTO.getAccountType());
    }

    public static AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(account.getAccountNumber(),account.getBalance(), account.getAccountType(), account.getOwnerName());
    }



}

