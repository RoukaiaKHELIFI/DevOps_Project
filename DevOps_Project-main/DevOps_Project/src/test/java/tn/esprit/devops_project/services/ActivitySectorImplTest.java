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

ActivitySector activitySector = new ActivitySector(1L,"code","libelle",null);
List<ActivitySector> activitySectors = new ArrayList<>(){
    {
        add(new ActivitySector(1L,"code","libelle",null));
        add(new ActivitySector(2L,"code","libelle",null));
    }
};
   @Test //JUNIT TEST
    void testRetrieveActivitySector() {
        AcService acService = new AcService();
        ActivitySector activitySector1 = acService.retrieveActivitySector(1L);
        assertNotNull(activitySector1);
    }

    @Test
    void retrieveAllActivitySectors() {
        Mockito.when(acRepository.findAll()).thenReturn(activitySectors);
        List<ActivitySector> activitySectors1 = AcService.retrieveAllActivitySectors();
        Assertions.assertEquals(2,activitySectors1.size());
    }

    @Test
    void addActivitySector() {
        Mockito.when(acRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);
        ActivitySector activitySector1 = AcService.addActivitySector(activitySector);
        Assertions.assertNotNull(activitySector1);
    }

    @Test
    void deleteActivitySector() {
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(activitySector));
        AcService.deleteActivitySector(1L);
        Mockito.verify(acRepository).deleteById(1L);
    }

   @Test //JUNIT Test
    void testUpdateActivitySector() {
        ActivitySector activitySector = new ActivitySector();
        AcService acService = new AcService(acRepository);
        ActivitySector activitySector1 = acService.updateActivitySector(activitySector);
        assertNotNull(activitySector1);
    }
}
