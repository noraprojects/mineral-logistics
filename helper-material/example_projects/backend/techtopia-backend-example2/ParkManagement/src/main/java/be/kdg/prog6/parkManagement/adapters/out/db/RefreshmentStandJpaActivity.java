package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.RefreshmentStandAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "parkmanagement", name = "ActionsStands")
@Getter
public class RefreshmentStandJpaActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID standID;

    @Setter
    @Enumerated(EnumType.STRING)
    private RefreshmentStandAction action;

    @Setter
    private LocalDateTime pit;

}
