package be.kdg.prog6.Park.adapters.in;

import be.kdg.prog6.Park.domain.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttractionDto {

    private String name;

    private int visitorCount = 0;
    private final Queue visitorQueue = new Queue();
    private Attraction.AttractionUUID attractionUUID;
    private String text;
    private Category category;
    private Tags tag;
    private AgeGroup ageGroup;
    private AttractionStatus status;
    private String image;
    private int miniumHeight;


    public AttractionDto(String name, int visitorCount, Attraction.AttractionUUID attractionUUID, String text, Category category, Tags tag, AgeGroup ageGroup, AttractionStatus status, String image, int miniumHeight) {
        this.name = name;
        this.visitorCount = visitorCount;
        this.attractionUUID = attractionUUID;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.ageGroup = ageGroup;
        this.status = status;
        this.image = image;
        this.miniumHeight = miniumHeight;
    }
}
