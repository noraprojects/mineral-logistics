package be.kdg.prog6.Park.domain;

import java.util.LinkedList;
import java.util.UUID;

public class Queue {

    private final LinkedList<UUID> visitors = new LinkedList<>();
    private int visitorAdditionRate;  //  number of visitors that can be added per minute

    public void enqueue(UUID visitor) {
        visitors.addLast(visitor);
    }

    public UUID dequeue() {
        return visitors.pollFirst();
    }

    public int size() {
        return visitors.size();
    }

    public boolean isEmpty() {
        return visitors.isEmpty();
    }

    public int getVisitorAdditionRate() {
        return visitorAdditionRate;
    }

    public void setVisitorAdditionRate(int rate) {
        this.visitorAdditionRate = rate;
    }
}
