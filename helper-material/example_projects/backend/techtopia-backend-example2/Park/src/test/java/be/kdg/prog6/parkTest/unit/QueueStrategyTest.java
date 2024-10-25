package be.kdg.prog6.parkTest.unit;

import be.kdg.prog6.Park.domain.OffPeakQueueStrategy;
import be.kdg.prog6.Park.domain.PeakTimeQueueStrategy;
import be.kdg.prog6.Park.domain.Queue;
import be.kdg.prog6.Park.domain.QueueManagementStrategy;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class QueueStrategyTest {

    @Test
    public void testPeakTimeQueueStrategy() {
        QueueManagementStrategy strategy = new PeakTimeQueueStrategy();
        Queue queue = mock(Queue.class);

        int slowRate = 5;

        when(queue.size()).thenReturn(100);

        strategy.manageQueue(queue);

        verify(queue, times(1)).setVisitorAdditionRate(slowRate);
    }

    @Test
    public void testOffPeakQueueStrategy() {
        QueueManagementStrategy strategy = new OffPeakQueueStrategy();
        Queue queue = mock(Queue.class);

        int fastRate = 15;

        when(queue.isEmpty()).thenReturn(true);

        strategy.manageQueue(queue);

        verify(queue, times(1)).setVisitorAdditionRate(fastRate);
    }
}
