package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ActivitySectorImplTest {
    ActivitySectorRepository acRepository = Mockito.mock(ActivitySectorRepository.class);
    @Autowired
    ActivitySectorRepository acR;
    @InjectMocks
    ActivitySectorImpl AcService;

    ActivitySector activitySector = new ActivitySector(1L, "code", "libelle", null);
    @Test //Mockito Testing for Retrieve
    void retrieveActivitySector() {
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        ActivitySector activitySector1 = AcService.retrieveActivitySector(1L);
        Assertions.assertNotNull(activitySector1);
        System.out.println(activitySector1.getCodeSecteurActivite());
    }


    @Test //Junit test with Database
    void retrieveAllActivitySectors() {

        ActivitySector activitySector1 = new ActivitySector(1L, "code", "libelle", null);
        ActivitySector activitySector2 = new ActivitySector(2L, "code", "libelle", null);
        acR.save(activitySector1);
        acR.save(activitySector2);
        // Retrieving all activity sectors from the database
        Assertions.assertEquals(2, acR.findAll().size());
    }

    @Test //Juinit test with Database
    void addActivitySector() {
        acR.save(activitySector);
        Assertions.assertNotNull(acR.findById(activitySector.getIdSecteurActivite()));
    }

    @Test //Mockito Testing for Delete
    void deleteActivitySector() {
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        AcService.deleteActivitySector(1L);
        Mockito.verify(acRepository).deleteById(1L);
    }

    @Test // Junit Update with Database
    void updateActivitySector() {
        acR.save(activitySector);
        activitySector.setLibelleSecteurActivite("updated");
        activitySector.setCodeSecteurActivite("updated");
        AcService.updateActivitySector(activitySector);
        Assertions.assertNotSame(acR.findById(activitySector.getIdSecteurActivite()), AcService.updateActivitySector(activitySector));
    }
}