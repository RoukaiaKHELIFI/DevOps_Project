package tn.esprit.devops_project.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {
    ProductRepository pr= Mockito.mock(ProductRepository.class);
    StockRepository st= Mockito.mock(StockRepository.class);
    @InjectMocks
    ProductServiceImpl pdService;
    @InjectMocks
    StockServiceImpl ssService;
    Stock s1 = new Stock(3L,"ElectronicsProducts",null);
    Product p1=new Product(5L,"Smartphone",30000,15, ProductCategory.ELECTRONICS,s1);
    Product p2=new Product(6L,"Ipad",50000,30, ProductCategory.ELECTRONICS,s1);
    Product p3=new Product(7L,"Atomic Habits",1000,100, ProductCategory.BOOKS,s1);
    Product p4=new Product(8L,"Celio",15000,3, ProductCategory.CLOTHING,s1);
    @BeforeEach
    void addstock(){
        ssService.addStock(s1);
    }
    @Test
    @Order(1)
    void addProduct() {

        //Product p =new Product(10L,"Bershka",15000,3, ProductCategory.CLOTHING,null);
        Product product = new Product();
        product.setIdProduct(1L);
        product.setTitle("Bershka");
        product.setPrice(15000);
        product.setCategory(ProductCategory.CLOTHING);
        product.setQuantity(3);
        product.setStock(null);
        List<Product> test = new ArrayList<>();
        when(st.findById(3L)).thenReturn(Optional.ofNullable(s1));
        test.add(pdService.addProduct(product,s1.getIdStock()));
        Assertions.assertNotNull(test);
        Assertions.assertEquals(1, test.size());
        System.out.println("add completed..........");
    }

    @Test
    @Order(2)
    void retrieveProduct() {
        Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.of(p2));
        Product product =pdService.retrieveProduct(6L);
        Assertions.assertNotNull(product);
        System.out.println(p2.getTitle());
        System.out.println("retrieve Product completed..........");
    }

    @Test
    @Order(3)
    void retreiveAllProduct() {
        List<Product> products = new ArrayList<Product>() {

            {
                add(p2);
                add(p3);
                add(p4);
            }};
        when(pdService.retreiveAllProduct()).thenReturn(products);
        //test
        List<Product> pList = pdService.retreiveAllProduct();
        assertEquals(3, pList.size());
        System.out.println( pList);
        System.out.println("retreive AllProduct completed..........");

    }

    @Test
    @Order(4)
    void retrieveProductByCategory() {
        List<Product> products = new ArrayList<Product>() {

            {
                add(p1);
                add(p2);

            }};
        when(pdService.retrieveProductByCategory(ProductCategory.ELECTRONICS)).thenReturn(products);
        //test
        List<Product> pList = pdService.retrieveProductByCategory(ProductCategory.ELECTRONICS);
        assertEquals(2, pList.size());
        System.out.println( pList.toString());
        System.out.println("retrieve Product By Category completed..........");
    }

    @Test
    @Order(5)
    void deleteProduct() {
        Mockito.lenient().when(pr.findById(p1.getIdProduct())).thenReturn(Optional.of(p1));
        pdService.deleteProduct(5L);
        verify(pr).deleteById(p1.getIdProduct());
        System.out.println("product 1 deleted: "+p1.getTitle());
        System.out.println(" Delete is working correctly...!!");
    }

    @Test
    @Order(6)
    void retreiveProductStock() {
        List<Product> products = new ArrayList<Product>() {

            {
                add(p1);
                add(p2);
                add(p3);
                add(p4);

            }};
        when(pr.findByStockIdStock(s1.getIdStock())).thenReturn(products);
        List<Product> pList =pdService.retreiveProductStock(s1.getIdStock());
        assertEquals(4, pList.size());
        System.out.println( pList);
        System.out.println("retreive Product Stock completed..........");
    }
}