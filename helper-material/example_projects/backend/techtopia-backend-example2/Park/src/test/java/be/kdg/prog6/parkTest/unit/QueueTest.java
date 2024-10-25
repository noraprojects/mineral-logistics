package be.kdg.prog6.parkTest.unit;

import be.kdg.prog6.Park.domain.Queue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testEnqueueAndDequeue() {
        Queue queue = new Queue();


        UUID visitor1 = UUID.randomUUID();
        UUID visitor2 = UUID.randomUUID();
        UUID visitor3 = UUID.randomUUID();
        queue.enqueue(visitor1);
        queue.enqueue(visitor2);
        queue.enqueue(visitor3);

        assertEquals(3, queue.size(), "Queue size is incorrect after enqueuing.");

        assertEquals(visitor1, queue.dequeue(), "Dequeued visitor is not correct.");
        assertEquals(2, queue.size(), "Queue size is incorrect after dequeuing.");
    }

    @Test
    void testEmptyQueue() {
        Queue queue = new Queue();

        assertTrue(queue.isEmpty(), "Newly created queue should be empty.");
        assertNull(queue.dequeue(), "Dequeue on an empty queue should return null.");
    }
}
