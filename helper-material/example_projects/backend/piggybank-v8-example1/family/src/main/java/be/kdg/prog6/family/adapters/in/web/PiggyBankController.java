package be.kdg.prog6.family.adapters.in.web;

import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.ports.in.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@RestController
public class PiggyBankController {

    private final ReceivingMoneyUseCase receivingMoneyUseCase;


    private final BuyingCandyUseCase buyingCandyUseCase;

    private final PiggyBankBalanceQuery piggyBankBalanceQuery;

    public PiggyBankController(final ReceivingMoneyUseCase receivingMoneyUseCase, BuyingCandyUseCase buyingCandyUseCase, PiggyBankBalanceQuery piggyBankBalanceQuery) {
        this.receivingMoneyUseCase = receivingMoneyUseCase;
        this.buyingCandyUseCase = buyingCandyUseCase;
        this.piggyBankBalanceQuery = piggyBankBalanceQuery;
    }


    //curl -X POST http://localhost:8080/family/api/money/20/person/ef01c728-ce36-46b5-a110-84f53fdd9668
    @PostMapping("/money/{amount}/person/{uuid}")
    public void receiveMoney(@PathVariable BigDecimal amount, @PathVariable UUID uuid) {
        receivingMoneyUseCase.receiveMoney(new GiveMoneyCommand(amount, new Person.PersonUUID(uuid)));
    }

    @DeleteMapping("/money/{amount}/person/{uuid}")
    public void takeMoney(@PathVariable BigDecimal amount, @PathVariable UUID uuid) {
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(amount, new Person.PersonUUID(uuid)));
    }

    @GetMapping("/person/{uuid}")
    public BigDecimal getBalance(@PathVariable UUID uuid) {
        return piggyBankBalanceQuery.getBalanceForPiggyBankOwner(new Person.PersonUUID(uuid));
    }

    @GetMapping("/person/{uuid}/start/{start}/end/{end}")
    public BigDecimal getBalance(@PathVariable UUID uuid, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate end) {
        return piggyBankBalanceQuery.getBalance(new PiggyBankBalanceCommand(uuid, LocalDateTime.of(start, LocalTime.MIDNIGHT), LocalDateTime.of(end.plusDays(1), LocalTime.MIDNIGHT)));
    }

}
