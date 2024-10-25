package be.kdg.prog6.grandparents.adapters.out;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Types;
import java.util.UUID;

@Entity
@Table(schema = "grandparents", name = "gpa.piggybankprojection")
@Getter
public class PiggyBankProjectionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID piggyBankUUID;

    @NaturalId
    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID ownerUUID;

    @Setter
    @Column(precision = 12, scale = 4)
    private BigDecimal currentBalance = BigDecimal.ZERO;

    @PrePersist
    @PreUpdate
    public void pricePrecisionConvertion() {
        currentBalance.setScale(4, RoundingMode.HALF_UP);
    }
}
