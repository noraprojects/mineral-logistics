package be.kdg.prog6.parkManagement.domain;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Park {
    private static Park instance;
    private final String name;
    private final String location;
    private int dailyVisitorCount;
    private List<Attraction> attractions;
    private ActivityWindow activityWindow;
    private StandActivityWindow standActivityWindow;
    private LocalDateTime snapShotDate;

    private Park() {
        name = "TECH-TOPIA!";
        location = "ANTWERP";
        dailyVisitorCount = 0;
        this.activityWindow = new ActivityWindow();
        this.standActivityWindow = new StandActivityWindow();
        this.snapShotDate = null;
    }

    public static void resetInstance() {
        instance = null;
    }

    public static Park getInstance() {
        if (instance == null) {
            synchronized (Park.class) {
                if (instance == null) {
                    instance = new Park();
                }
            }
        }
        return instance;
    }

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }

    public void setActivityWindow(StandActivityWindow activityWindow) {
        this.standActivityWindow = activityWindow;
    }

    public StandActivityWindow getStandActivityWindow() {
        return standActivityWindow;
    }

    public VisitorActivity incrementDailyVisitorCount(UUID user) {
        VisitorActivity activity = new VisitorActivity(VisitorAction.Enter, user, LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public VisitorActivity decreaseDailyVisitorCount(UUID user) {
        VisitorActivity activity = new VisitorActivity(VisitorAction.Leave, user, LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public RefreshmentsActivity addStand(UUID stand) {
        RefreshmentsActivity activity = new RefreshmentsActivity(RefreshmentStandAction.OPEN, stand, LocalDateTime.now());
        standActivityWindow.add(activity);
        return activity;
    }

    public RefreshmentsActivity removeStand(UUID stand) {
        RefreshmentsActivity activity = new RefreshmentsActivity(RefreshmentStandAction.CLOSE, stand, LocalDateTime.now());
        standActivityWindow.add(activity);
        return activity;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int calculateDailyVisitorCount() {
        return activityWindow.calculateDailyVisitorCount();
    }

    public int getCurrentVisitorCount() {
        return activityWindow.calculateVisitorCount();
    }

    public int calculateStandsAmount() {
        return standActivityWindow.calculateStandsAmount();
    }

    public int getDailyVisitorCount() {
        return dailyVisitorCount;
    }

    public LocalDateTime getSnapShotDate() {
        return snapShotDate;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void snapShotVisitorCount() {
        dailyVisitorCount = calculateDailyVisitorCount();
        snapShotDate = LocalDateTime.now();

    }

    public void addParkActivity(VisitorActivity visitorActivity) {
        this.activityWindow.add(visitorActivity);
    }

    public void addStandActivity(RefreshmentsActivity refreshmentstandActivity) {
        this.standActivityWindow.add(refreshmentstandActivity);
    }
}
