package be.kdg.prog6.family.ports.in;

import be.kdg.prog6.family.domain.Person;

import java.math.BigDecimal;
import java.util.UUID;

public record BuyCandyAmountCommand(BigDecimal money, Person.PersonUUID buyer) {
}
