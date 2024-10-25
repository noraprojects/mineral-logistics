package be.kdg.prog6.Park.adapters.out.db;

import be.kdg.prog6.Park.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(schema = "park", name = "Stand")
@Getter
@Setter
public class StandJpaEntity {
    @Id
    private UUID uuid;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int throughput;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tags tag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttractionStatus attractionStatus;


    @Column(nullable = false)
    private String image;


    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int miniumheight;


    public StandJpaEntity(UUID uuid, String name, int throughput, Category category, Tags tag, AgeGroup ageGroup, AttractionStatus attractionStatus, String image, String text, int miniumHeight) {
        this.uuid = uuid;
        this.name = name;
        this.throughput = throughput;
        this.category = category;
        this.tag = tag;
        this.ageGroup = ageGroup;
        this.attractionStatus = attractionStatus;
        this.image = image;
        this.text = text;
        this.miniumheight = miniumHeight;
    }

    public StandJpaEntity() {

    }
}
