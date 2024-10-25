package be.kdg.prog6.family.adapters.out.db;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "family", name = "family.piggybanks")
public class PiggyBankJpaEntity {

    public PiggyBankJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;

    @Column(precision = 12, scale = 4)
    private BigDecimal baseBalance = BigDecimal.ZERO;

    private LocalDateTime baseBalanceDate;

    public PiggyBankJpaEntity() {
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setBaseBalance(BigDecimal baseBalance) {
        this.baseBalance = baseBalance;
    }

    public void setBaseBalanceDate(LocalDateTime baseBalanceDate) {
        this.baseBalanceDate = baseBalanceDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getOwner() {
        return owner;
    }

    public BigDecimal getBaseBalance() {
        return baseBalance;
    }

    public LocalDateTime getBaseBalanceDate() {
        return baseBalanceDate;
    }


    @PrePersist
    @PreUpdate
    public void pricePrecisionConvertion() {
        baseBalance.setScale(4, RoundingMode.HALF_UP);
    }
}
