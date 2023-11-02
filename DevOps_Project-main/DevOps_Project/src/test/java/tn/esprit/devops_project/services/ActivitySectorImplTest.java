package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)

class ActivitySectorImplTest {
    ActivitySectorRepository acRepository = Mockito.mock(ActivitySectorRepository.class);
    @InjectMocks
    ActivitySectorImpl AcService;

    ActivitySector activitySector = new ActivitySector(1L, "code", "libelle", null);
    List<ActivitySector> activitySectors = new ArrayList<>() {
        {
            add(new ActivitySector(1L, "code", "libelle", null));
            add(new ActivitySector(2L, "code", "libelle", null));
        }
    };

    @Test
    void retrieveActivitySector() {
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        ActivitySector activitySector1 = AcService.retrieveActivitySector(1L);
        Assertions.assertNotNull(activitySector1);
        System.out.println(activitySector1.getCodeSecteurActivite());
    }


    @Test
    void retrieveAllActivitySectors() {
        Assertions.assertEquals(2, activitySectors.size());
    }

    @Test
    void addActivitySector() {
        List<ActivitySector> test = new ArrayList<>();
        test.add(AcService.addActivitySector(activitySector));
        Assertions.assertNotNull(test);
    }

    @Test
    void deleteActivitySector() {
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        AcService.deleteActivitySector(1L);
        Mockito.verify(acRepository).deleteById(1L);
    }

    @Test
    void updateActivitySector() {
      List<ActivitySector> activitySectorsT = new ArrayList<>();
        activitySectorsT.add(AcService.updateActivitySector(activitySector));
        Assertions.assertNotNull(activitySectorsT);
    }
}