package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActivitySectorImplTest {
@Autowired
    IActivitySector iActivitySector;
    @Test
    void retrieveAllActivitySectors() {
        List<ActivitySector> activitySectors = iActivitySector.retrieveAllActivitySectors();
        Assertions.assertEquals(0, activitySectors.size());
    }

    /*@Test
    void addActivitySector() {
    }

    @Test
    void deleteActivitySector() {
    }

    @Test
    void updateActivitySector() {
    }

    @Test
    void retrieveActivitySector() {
    }

    */
}