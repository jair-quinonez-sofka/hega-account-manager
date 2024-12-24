package co.com.sofkau.mapper;

import co.com.sofkau.Account;
import co.com.sofkau.data.AccountEntity;

public class AccountMapper {
    public static Account toAccount(AccountEntity accountEntity) {
        return new Account(accountEntity.getId(), accountEntity.getAccountNumber(),
                accountEntity.getBalance(), accountEntity.getOwnerName(),
                accountEntity.getAccountType());
    }
    public static AccountEntity toAccountEntity(Account account) {
        return new AccountEntity(account.getId(), account.getAccountNumber(),
                account.getBalance(), account.getOwnerName(),
                account.getAccountType());
    }

}
