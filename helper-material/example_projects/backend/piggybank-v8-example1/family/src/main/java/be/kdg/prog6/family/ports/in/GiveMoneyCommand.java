package be.kdg.prog6.family.ports.in;

import be.kdg.prog6.family.domain.Person;

import java.math.BigDecimal;

public record GiveMoneyCommand(BigDecimal amount, Person.PersonUUID personUUID) {
}
