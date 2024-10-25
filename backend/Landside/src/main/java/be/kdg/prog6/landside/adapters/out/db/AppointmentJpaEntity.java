package be.kdg.prog6.landside.adapters.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="appointment", name = "appointment.appointments")
public class AppointmentJpaEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID seller;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID LicensePlate;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    public AppointmentJpaEntity(UUID uuid, UUID seller, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.uuid = uuid;
        this.seller = seller;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public AppointmentJpaEntity() {}


}
