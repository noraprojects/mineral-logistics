package be.kdg.prog6.Admission;

import be.kdg.prog6.Admission.domain.TicketCodeUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class validateTest {

    @Test
    public void testValidUUID() {
        String validUUID = "ef01c728-ce36-46b5-a110-84f53fdd9668";
        assertTrue(TicketCodeUtil.isValidUUID(validUUID));
    }

    @Test
    public void testInvalidUUID() {
        String invalidUUID = "invalid-uuid";
        assertFalse(TicketCodeUtil.isValidUUID(invalidUUID));
    }

    @Test
    public void testNullUUID() {
        assertFalse(TicketCodeUtil.isValidUUID(null));
    }

    @Test
    public void testShortUUID() {
        String shortUUID = "short-uuid";
        assertFalse(TicketCodeUtil.isValidUUID(shortUUID));
    }
}
