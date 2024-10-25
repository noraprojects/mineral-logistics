package be.kdg.prog6.parkManagement.adapters.out.db;


import be.kdg.prog6.parkManagement.domain.VisitorAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="ParkManagement",name = "parkManagement_visitor_activities")
@Getter
public class ParkJpaActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Setter
    @Enumerated(EnumType.STRING)
    private VisitorAction visitorAction;


    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID user;

    @Setter
    private LocalDateTime pit;

}
