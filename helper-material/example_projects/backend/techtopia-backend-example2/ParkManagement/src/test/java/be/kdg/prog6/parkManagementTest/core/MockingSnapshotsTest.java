package be.kdg.prog6.parkManagementTest.core;


import be.kdg.prog6.parkManagement.domain.*;
import be.kdg.prog6.parkManagement.ports.out.ParkActivityCreatePort;
import be.kdg.prog6.parkManagement.ports.out.ParkUpdatePort;
import be.kdg.prog6.parkManagement.ports.out.VisitorsLoadPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockingSnapshotsTest {

    private static final Guest.GuestUUID DEFINITELY_NOT_BOBBY_ID = new Guest.GuestUUID(UUID.randomUUID());
    private static final Guest.GuestUUID USER2 = new Guest.GuestUUID(UUID.randomUUID());
    private static final Guest.GuestUUID USER3 = new Guest.GuestUUID(UUID.randomUUID());
    private static final Guest.GuestUUID USER4 = new Guest.GuestUUID(UUID.randomUUID());
    private static final Guest.GuestUUID USER5 = new Guest.GuestUUID(UUID.randomUUID());


    @Mock
    ParkUpdatePort parkUpdatePort;

    @Mock
    VisitorsLoadPort parkVistorsLoadPort;

    @Mock
    ParkActivityCreatePort parkActivityCreatePort;

    @Captor
    ArgumentCaptor<Visitor> parkArgumentCaptor;


    @BeforeEach
    void setUp() {
        Park.resetInstance();
    }


    @Test
    void retrievingTotalVisitors() {


        Park park = Park.getInstance();

        park.snapShotVisitorCount();

        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, DEFINITELY_NOT_BOBBY_ID.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER2.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER3.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER5.uuid(), LocalDateTime.now()));

        assertEquals(5, park.calculateDailyVisitorCount());

    }


    @Test
    void retrievingTotalVisitorsWithDismiss() {


        Park park = Park.getInstance();

        park.snapShotVisitorCount();

        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, DEFINITELY_NOT_BOBBY_ID.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER2.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER3.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER5.uuid(), LocalDateTime.now()));


        park.addParkActivity(new VisitorActivity(VisitorAction.Leave, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Leave, USER5.uuid(), LocalDateTime.now()));

        assertEquals(5, park.calculateDailyVisitorCount());

    }


    @Test
    void retrievingCurrentVisitorsWithDismiss() {


        Park park = Park.getInstance();

        park.snapShotVisitorCount();

        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, DEFINITELY_NOT_BOBBY_ID.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER2.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER3.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER5.uuid(), LocalDateTime.now()));


        park.addParkActivity(new VisitorActivity(VisitorAction.Leave, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Leave, USER5.uuid(), LocalDateTime.now()));

        assertEquals(3, park.getCurrentVisitorCount());

    }

    @Test
    void snapshotTest() {
        Park park = Park.getInstance();

        //INITIAL SNAPSHOT
        park.snapShotVisitorCount();

        // ONE ENTERS THE PARK
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, DEFINITELY_NOT_BOBBY_ID.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER2.uuid(), LocalDateTime.now()));


        //NEW DAY BEGINS
        //Scheduled after 60 SECONDS
        park.snapShotVisitorCount();


        // 4 PEOPLE ENTER THE PARK
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER2.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER3.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER4.uuid(), LocalDateTime.now()));
        park.addParkActivity(new VisitorActivity(VisitorAction.Enter, USER5.uuid(), LocalDateTime.now()));


        // Expect 4 because BOBBY was on the day before ( 60 seconds )
        assertEquals(4, park.calculateDailyVisitorCount());
    }


}
