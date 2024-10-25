package be.kdg.prog6.Park.domain;


import java.util.UUID;

//singleton
public class QueueManager {
    private static QueueManager instance;

    private QueueManager() {
    }

    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    public void manageQueue(Queue queue, UUID visitorId) {
        queue.enqueue(visitorId);
    }
}
