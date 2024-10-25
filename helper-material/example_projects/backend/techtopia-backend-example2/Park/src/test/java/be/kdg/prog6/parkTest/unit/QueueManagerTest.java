package be.kdg.prog6.parkTest.unit;

import be.kdg.prog6.Park.domain.QueueManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueManagerTest {

    @Test
    public void testSingletonPattern() {
        QueueManager instance1 = QueueManager.getInstance();
        QueueManager instance2 = QueueManager.getInstance();

        assertEquals(instance1, instance2, "Instances are not the same!");
    }
}
