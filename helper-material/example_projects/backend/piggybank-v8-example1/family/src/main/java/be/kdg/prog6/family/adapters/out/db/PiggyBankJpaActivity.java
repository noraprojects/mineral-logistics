package be.kdg.prog6.family.adapters.out.db;

import be.kdg.prog6.family.domain.PiggyBankAction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="family",name = "family.activities")
@Getter
public class PiggyBankJpaActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Setter
    @Enumerated(EnumType.STRING)
    private PiggyBankAction piggyBankAction;

    @Column(precision = 12, scale = 4)
    @Setter
    private BigDecimal amount =  BigDecimal.ZERO;

    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID piggyBank;

    @Setter
    private LocalDateTime pit;

    @PrePersist
    @PreUpdate
    public void pricePrecisionConvertion() {
        amount.setScale(4, RoundingMode.HALF_UP);
    }
}
