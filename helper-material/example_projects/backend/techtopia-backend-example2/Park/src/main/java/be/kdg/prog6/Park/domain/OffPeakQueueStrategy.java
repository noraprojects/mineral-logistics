package be.kdg.prog6.Park.domain;

public class OffPeakQueueStrategy implements QueueManagementStrategy{
    @Override
    public void manageQueue(Queue queue) {
        queue.setVisitorAdditionRate(15);
    }
}
