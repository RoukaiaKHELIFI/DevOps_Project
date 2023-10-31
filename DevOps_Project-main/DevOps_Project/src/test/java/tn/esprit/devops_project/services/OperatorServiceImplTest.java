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




    @Test
    void retrieveAllOperators() {
        Mockito.when(operatorRepository.findAll()).thenReturn(operators);
        List<Operator> operator1 = operatorService.retrieveAllOperators();
        Assertions.assertEquals(2,operator1.size());


    }




    @Test
    void addOperator() {
        Mockito.when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);
        Operator operator1 = operatorService.addOperator(operator);
        Assertions.assertNotNull(operator1);
    }



    @Test
    void retrieveOperator() {
        Mockito.when(operatorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(operator));
        Operator operator1 = operatorService.retrieveOperator(1L);
        Assertions.assertNotNull(operator1);
        System.out.println(operator1.getLname());
    }
}





