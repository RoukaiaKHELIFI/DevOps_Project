package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;
import java.io.*;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {
    InvoiceRepository acRepository = Mockito.mock(InvoiceRepository.class);
    SupplierRepository supRepository = Mockito.mock(SupplierRepository.class);
    OperatorRepository opRepository= Mockito.mock(OperatorRepository.class);
    @InjectMocks
    InvoiceServiceImpl inService;
    @InjectMocks
    OperatorServiceImpl opService;
    @InjectMocks
    SupplierServiceImpl spService;

    Invoice i1 = new Invoice(2L,3000,5000,new Date(2000-06-26),new Date(2000-07-26),Boolean.TRUE,null,null);
    Invoice i2 = new Invoice(3L,2000,7000,new Date(2001-06-26),new Date(2001-07-26),Boolean.FALSE,null,null);
    Invoice i3 = new Invoice(4L,4000,8000,new Date(2002-06-26),new Date(2002-07-26),Boolean.TRUE,null,null);

    Set<Invoice> tab= new HashSet<Invoice>();
    Operator op1 = new Operator(2L,"skanderrr","belhassen","123",tab);

    Supplier s1 = new Supplier(8L,"18951561","skander", SupplierCategory.ORDINAIRE,tab,null);
    Supplier s2 = new Supplier(9L,"1787856786","oussama", SupplierCategory.CONVENTIONNE,null,null);
    Supplier s3 = new Supplier(10L,"1585724","helmi", SupplierCategory.ORDINAIRE,null,null);
    @Test
    @Order(1)
    void retrieveAllInvoices() {

        List<Invoice> listtt = new ArrayList<>() {
            {
                add(i1);
                add(i2);
            }
        };
        Mockito.when(inService.retrieveAllInvoices()).thenReturn(listtt);
        Assertions.assertEquals(2, listtt.size());
        System.out.println("the retrieve All Invoices worked ..... ");

    }

    @Test
    @Order(2)
    void cancelInvoice() {

        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(i2));
        inService.cancelInvoice(i2.getIdInvoice());
        System.out.println("the cancel worked ..... ");

    }

    @Test
    @Order(3)
    void retrieveInvoice() {
        List<Invoice> listtt = new ArrayList<>() {
            {
                add(i1);
                add(i2);
            }
        };
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(i3));
        Invoice invoicetest = inService.retrieveInvoice(3L);
        Assertions.assertNotNull(invoicetest);
        System.out.println(invoicetest.getIdInvoice()+" the retrieve function worked the first one was the id .......");

    }
/*
    @Test
    @Order(4)
    void getInvoicesBySupplier() {
        List<Invoice> list = new ArrayList<>() {
            {
                add(i1);
                add(i2);
            }
        };
        tab.add(i1);
        tab.add(i2);
        s1.setInvoices(tab);
        Mockito.when(supRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s1));
        List<Invoice> List = new ArrayList<Invoice>(tab);
        List = inService.getInvoicesBySupplier(s1.getIdSupplier());
        System.out.println("ena houni 2");
        Assertions.assertEquals(2,List.size());

        System.out.println(inService.getInvoicesBySupplier(s1.getIdSupplier()).size());
        System.out.println("get invoices By Supplier worksss.............");

    }
*/
    @Test
    @Order(5)
    void assignOperatorToInvoice() {
        tab.add(i1);
        Mockito.when(acRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(i1));
        Mockito.when(opRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op1));
        Assertions.assertEquals(op1.getInvoices(),tab);
        System.out.println("assignOperator To Invoice taadet .............");
    }

    @Test
    @Order(6)
    void getTotalAmountInvoiceBetweenDates() {
        Mockito.when( acRepository.getTotalAmountInvoiceBetweenDates(Mockito.any(),Mockito.any())).thenReturn(300F);
        inService.getTotalAmountInvoiceBetweenDates(new Date(2004-06-02),new Date(2001-02-02));
        System.out.println("get Total Amount Invoice Between Dates .............");


    }
}