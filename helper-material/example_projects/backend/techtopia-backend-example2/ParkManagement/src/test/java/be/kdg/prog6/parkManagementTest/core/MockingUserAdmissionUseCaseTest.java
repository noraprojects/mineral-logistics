package be.kdg.prog6.parkManagementTest.core;

import be.kdg.prog6.parkManagement.core.DefaultUserAdmittedProjector;
import be.kdg.prog6.parkManagement.events.UserAdmittedActivityEvent;
import be.kdg.prog6.parkManagement.ports.in.UserAdmittedProjector;
import be.kdg.prog6.parkManagement.ports.out.ParkActivityCreatePort;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MockingUserAdmissionUseCaseTest {


    @Test
    void admitUser() {
        // Create a mock for the ParkActivityCreatePort
        ParkActivityCreatePort parkActivityCreatePort = mock(ParkActivityCreatePort.class);

        // Create an instance of DefaultUserAdmittedProjector with the mock port
        DefaultUserAdmittedProjector userAdmittedProjector = new DefaultUserAdmittedProjector(
                List.of(parkActivityCreatePort)
        );

        // Define the event you want to use for testing
        UserAdmittedActivityEvent event = new UserAdmittedActivityEvent(UUID.randomUUID());

        // Call the admitUser method to be tested
        userAdmittedProjector.admitUser(event);

        // Verify that the createParkActivity method of the mock port is called once
        verify(parkActivityCreatePort, times(1)).createParkActivity(any(), any());
    }


    @Test
    void admitUser2() {
        AtomicInteger a = new AtomicInteger();

        List<ParkActivityCreatePort> parkActivityCreatePortList = new ArrayList<>();
        parkActivityCreatePortList.add((parkActivity, visitorActivity) -> {
            a.getAndIncrement();
        });

        UserAdmittedProjector userAdmittedProjector = new DefaultUserAdmittedProjector(parkActivityCreatePortList);

        UserAdmittedActivityEvent event = new UserAdmittedActivityEvent(UUID.randomUUID());

        userAdmittedProjector.admitUser(event);

        assertEquals(a.intValue(), 1);
    }


}
