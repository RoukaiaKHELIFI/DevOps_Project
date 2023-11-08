package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;

import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

    Supplier supplier = new Supplier(1L,"S001","Supplier 1",SupplierCategory.ORDINAIRE,null,null);

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



    // JUnit
    @Test
    void retrieveAllSuppliers() {
        List<Supplier> supplierList = supplierServiceimpl.retrieveAllSuppliers();
        assertEquals(0, supplierList.size());
        System.out.println("Retrieve All Suppliers Completed");
    }


    // JUnit
    @Test
    void addSupplier() {
        List<Supplier> test = new ArrayList<>();
        test.add(supplierServiceimpl.addSupplier(supplier));
        Assertions.assertNotNull(test);
        Assertions.assertEquals(1, test.size());
        System.out.println("Add Supplier Completed");
    }

    //JUnit
    @Test
    void updateSupplier() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplierServiceimpl.updateSupplier(supplier));
        Assertions.assertNotNull(supplierList);
        System.out.println("Update Completed");
    }


    // Mockito
    @Test
    void retrieveSupplier() {
        when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Supplier s1 = supplierServiceimpl.retrieveSupplier(1L);
        Assertions.assertNotNull(s1);
        System.out.println(s1.getCode());
        System.out.println("Retrieve Supplier Completed");
    }

    // Mockito
    @Test
    void deleteSupplier() {
        Mockito.doNothing().when(supplierRepository).deleteById(Mockito.anyLong());
        supplierServiceimpl.deleteSupplier(1L);
        Mockito.verify(supplierRepository, Mockito.times(1)).deleteById(1L);
        System.out.println("Delete Supplier Completed");
    }




}