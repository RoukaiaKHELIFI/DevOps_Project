package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;

import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
@Mock
ProductRepository productRepository ;
@InjectMocks
ProductServiceImpl productServiceImpl ;

Product product = new Product(1L,"prod1",12,14,null,null);

    List<Product> products = new ArrayList<>(){
        {
            add(new Product(2L,"prod2",13,120,null,null));
            add(new Product(3L,"prod3",14,2,null,null));
        }
    };
    Set<Product> foo = new HashSet<>(products);
    Stock stock = new Stock(55L,"stock1", foo);
    @Test
    void addProduct() {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Product product1 = productServiceImpl.addProduct(product, stock.getIdStock());
        Assertions.assertNotNull(product1);
    }

    @Test
    void retrieveProduct() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Product product1 = productServiceImpl.retrieveProduct(1L);
        Assertions.assertNotNull(product1);
        System.out.println(product1.getPrice());

    }

    @Test
    void retreiveAllProduct() {
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<Product> products1 = productServiceImpl.retreiveAllProduct();
        Assertions.assertEquals(2,products1.size());
    }
/*
    @Test
    void retrieveProductByCategory() {
        Mockito.when(productRepository.findByCategory(ProductCategory.valueOf(Mockito.anyString()))).thenReturn(Collections.singletonList(product));
        Product product1 = productServiceImpl.retrieveProduct(1L);
        List<Product> products = productServiceImpl.retrieveProductByCategory(Optional.of(product1).get().getCategory());
        // Check if products were retrieved
        Assertions.assertNotNull(products);
        Assertions.assertFalse(products.isEmpty());

    }
*/

    @Test
    void deleteProduct() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        productServiceImpl.deleteProduct(1L);
        Mockito.verify(productRepository).deleteById(1L);
    }


}