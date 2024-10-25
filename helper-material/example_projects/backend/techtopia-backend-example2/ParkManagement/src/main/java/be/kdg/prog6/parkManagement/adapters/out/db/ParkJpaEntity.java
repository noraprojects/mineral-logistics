package be.kdg.prog6.parkManagement.adapters.out.db;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "park", name = "park_activity_snapshot")
public class ParkJpaEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column(name = "people_count", nullable = false)
    private int peopleCount;


    private LocalDateTime snapshotTimestamp;


    public ParkJpaEntity() {
    }
    
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public LocalDateTime getSnapshotTimestamp() {
        return snapshotTimestamp;
    }

    public void setSnapshotTimestamp(LocalDateTime snapshotTimestamp) {
        this.snapshotTimestamp = snapshotTimestamp;
    }

    @PrePersist
    public void onPrePersist() {
        if (this.snapshotTimestamp == null) { // if snapshotTimestamp wasn't set manually, set it to "now"
            this.snapshotTimestamp = LocalDateTime.now();
        }
    }
}
