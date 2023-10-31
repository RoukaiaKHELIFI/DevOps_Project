package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;

import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    Supplier s = new Supplier(1L,"S001","Supplier 1",SupplierCategory.ORDINAIRE,null,null);

    List<Supplier> suppliers = new ArrayList<>(){
        {
            add(new Supplier(2L,"S002","Supplier 2",SupplierCategory.ORDINAIRE,null,null));
            add(new Supplier(3L,"S003","Supplier 3",SupplierCategory.CONVENTIONNE,null,null));
        }
    };

    @Mock
    SupplierRepository supplierRepository ;
    @InjectMocks
    SupplierServiceImpl supplierServiceimpl ;

    @Test
    void retrieveSupplier() {
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Supplier s1 = supplierServiceimpl.retrieveSupplier(1L);
        Assertions.assertNotNull(s1);
        System.out.println(s1.getCode());
    }

    @Test
    void retrieveAllSuppliers() {
        Mockito.when(supplierRepository.findAll()).thenReturn(suppliers);
        List<Supplier> s1 = supplierServiceimpl.retrieveAllSuppliers();
        Assertions.assertEquals(2,s1.size());
    }

    @Test
    void addSupplier() {
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(s);
        Supplier s1 = supplierServiceimpl.addSupplier(s);
        Assertions.assertNotNull(s1);
    }


}