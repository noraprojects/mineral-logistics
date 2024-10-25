package be.kdg.prog6.grandparents.adapters.out;

import be.kdg.prog6.grandparents.domain.PiggyBankAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@Table(schema = "grandparents", name = "gpa.piggybankactivities")
@Getter
public class PiggyBankActivityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 12, scale = 4)
    @Setter
    private BigDecimal amount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Setter
    private PiggyBankAction action;

    @Setter
    private LocalDateTime pit;


    @PrePersist
    @PreUpdate
    public void amountPrecisionConvertion() {
        amount.setScale(4, RoundingMode.HALF_UP);
    }

}
