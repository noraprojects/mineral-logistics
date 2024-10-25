package be.kdg.prog6.family.ports.in;

public interface ReceivingMoneyUseCase {
    void receiveMoney(GiveMoneyCommand giveMoneyCommand);
}
