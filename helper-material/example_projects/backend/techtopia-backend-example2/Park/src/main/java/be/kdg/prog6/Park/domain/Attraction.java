package be.kdg.prog6.Park.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Attraction {
    private final Queue visitorQueue = new Queue();
    private final ActivityWindow activityWindow;
    private String name;
    private int visitorCount = 0;
    private AttractionUUID attractionUUID;
    private String text;
    private Category category;
    private Tags tag;
    private AgeGroup ageGroup;
    private AttractionStatus status;
    private String image;

    private int miniumHeight;


    public Attraction(String name, int visitorCount, AttractionUUID attractionUUID,
                      String text, Category category, Tags tag, AgeGroup ageGroup, AttractionStatus status, int
                              throughput, int miniumHeight, String image) {
        this.name = name;
        this.visitorCount = visitorCount;
        this.activityWindow = new ActivityWindow();
        this.attractionUUID = attractionUUID;
        this.text = text;
        this.category = category;
        this.tag = tag;
        this.ageGroup = ageGroup;
        this.status = status;
        this.visitorQueue.setVisitorAdditionRate(throughput);
        this.miniumHeight = miniumHeight;
        this.image = image;
    }

    public Attraction(String name, Category category, AttractionUUID attractionUUID) {
        this.name = name;
        this.category = category;
        this.status = AttractionStatus.closed;
        this.attractionUUID = attractionUUID;
        this.activityWindow = new ActivityWindow();

    }

    public Attraction(String name, Category category) {
        this.name = name;
        this.category = category;
        this.status = AttractionStatus.closed;
        this.activityWindow = new ActivityWindow();

        this.attractionUUID = new AttractionUUID(UUID.randomUUID());
    }

    public int getMiniumHeight() {
        return miniumHeight;
    }

    public void setMiniumHeight(int miniumHeight) {
        this.miniumHeight = miniumHeight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Queue getVisitorQueue() {
        return visitorQueue;
    }

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                ", visitorCount=" + visitorCount +
                ", visitorQueue=" + visitorQueue +
                ", activityWindow=" + activityWindow +
                ", attractionUUID=" + attractionUUID +
                ", text='" + text + '\'' +
                ", category=" + category +
                ", tag=" + tag +
                ", ageGroup=" + ageGroup +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", miniumHeight=" + miniumHeight +
                '}';
    }

    public AttractionQueueActivity admitVisitor(Ticket.TicketUUID visitorUUID, AttractionUUID attractionUUID) {
        AttractionQueueActivity activity = new AttractionQueueActivity(QueueAction.Enter, visitorUUID, LocalDateTime.now(), attractionUUID);
        activityWindow.add(activity);
        return activity;
    }

    public AttractionQueueActivity dischargeVisitor(Ticket.TicketUUID visitorUUID, AttractionUUID attractionUUID) {
        AttractionQueueActivity activity = new AttractionQueueActivity(QueueAction.Leave, visitorUUID, LocalDateTime.now(), attractionUUID);
        activityWindow.add(activity);
        return activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public AttractionStatus getStatus() {
        return status;
    }

    public void setStatus(AttractionStatus status) {
        this.status = status;
    }

    public AttractionUUID getAttractionUUID() {
        return attractionUUID;
    }

    public void setAttractionUUID(AttractionUUID attractionUUID) {
        this.attractionUUID = attractionUUID;
    }

    public int getVisitorCount() {
        return activityWindow.calculateVisitors();
    }

    public void addAttractionActivity(AttractionQueueActivity attractionQueueActivity) {
        this.activityWindow.add(attractionQueueActivity);
        this.visitorCount = activityWindow.calculateVisitors();
    }

    public void visitorJoinsQueue(UUID visitorId) {
        visitorQueue.enqueue(visitorId);
    }

    public record AttractionUUID(UUID uuid) {
    }
}
