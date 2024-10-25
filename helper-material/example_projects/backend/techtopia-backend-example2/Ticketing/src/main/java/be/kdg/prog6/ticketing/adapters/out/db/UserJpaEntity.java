package be.kdg.prog6.ticketing.adapters.out.db;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(schema = "user", name = "user.guest")
public class UserJpaEntity {



    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private String userName;


    @JdbcTypeCode(Types.VARCHAR)
    private UUID userID;

    public UserJpaEntity(String username, UUID userID) {
        this.userName = username;
        this.userID = userID;
    }

    public UserJpaEntity() {

    }
}
