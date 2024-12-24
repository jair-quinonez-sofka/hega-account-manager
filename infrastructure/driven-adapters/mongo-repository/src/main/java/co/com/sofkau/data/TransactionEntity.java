package co.com.sofkau.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transactions")
public class TransactionEntity {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    @Field(name = "ammount")
    private BigDecimal amount;

    @Field(name = "transaction_type_showed")
    private String transactionType;

    @Field(name = "transaction_fee")
    private BigDecimal transactionFee;

    @Field(name = "created_at")
    private LocalDateTime timestamp;


    @DBRef
    @Field(name = "account")
    private AccountEntity account;


    @DBRef
    @Field(name = "card")
    private CardEntity card;
}
