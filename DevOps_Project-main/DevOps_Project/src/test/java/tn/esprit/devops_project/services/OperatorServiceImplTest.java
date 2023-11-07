package tn.esprit.devops_project.services;




import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.Iservices.IOperatorService;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;

import tn.esprit.devops_project.repositories.OperatorRepository;



import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {
    @Mock
    OperatorRepository operatorRepository;
    @InjectMocks
    OperatorServiceImpl operatorService;




    // Initialisation des données
    Operator operator = new Operator(1L, "John", "Doe", "password123", null);
    List<Operator> operators = new ArrayList<>(){
        {
            add(new Operator(1L, "John", "Doe", "password123", null));
            add(new Operator(2L, "Jane", "Doe", "password456", null));
            // Ajoutez autant d'opérateurs que vous en avez besoin.
        }
    };



/*
    @Test
    void retrieveAllOperators() {
        Mockito.when(operatorRepository.findAll()).thenReturn(operators);
        List<Operator> operator1 = operatorService.retrieveAllOperators();
        Assertions.assertEquals(2,operator1.size());


    }

*/

/*
    @Test
    void addOperator() {
        Mockito.when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);
        Operator operator1 = operatorService.addOperator(operator);
        Assertions.assertNotNull(operator1);
    }
*/

    //Mockito
    @Test
    void retrieveOperator() {
        Mockito.when(operatorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(operator));
        Operator operator1 = operatorService.retrieveOperator(1L);
        Assertions.assertNotNull(operator1);
        System.out.println(operator1.getLname());
    }
    //Mockito
    @Test
    void deleteOperator() {
        Mockito.doNothing().when(operatorRepository).deleteById(Mockito.anyLong());

        operatorService.deleteOperator(1L);

        Mockito.verify(operatorRepository, Mockito.times(1)).deleteById(1L);

    }
    //JUnit
    @Test
    void updateOperator() {
        List<Operator> operatorList = new ArrayList<>();
        operatorList.add(operatorService.updateOperator(operator));
        Assertions.assertNotNull(operatorList);
    }
    // JUnit
    @Test
    void retrieveAllOperators() {

        when(operatorService.retrieveAllOperators()).thenReturn(operators);
        //test
        List<Operator> operatorList = operatorService.retrieveAllOperators();
        assertEquals(2, operatorList.size());
        System.out.println( operatorList);
        System.out.println("Retreive All Operators Completed..........");
    }

    // JUnit
    @Test
    void addOperator() {
        List<Operator> test = new ArrayList<>();
        test.add(operatorService.addOperator(operator));
        Assertions.assertNotNull(test);
    }
}





