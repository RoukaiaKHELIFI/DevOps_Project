package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;


import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    Stock s = new Stock(1L,"stock1",null);


    @Mock
    StockRepository stockRepository ;
    @InjectMocks
    StockServiceImpl stockServiceImpl ;
    @Test
    void addStock() {
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(s);
        Stock s2 = stockServiceImpl.addStock(s);
        Assertions.assertNotNull(s2);
    }

    @Test
    void retrieveStock() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Stock s1 = stockServiceImpl.retrieveStock(1L);
        Assertions.assertNotNull(s1);
        System.out.println(s1.getTitle());
    }

    @Test
    void retrieveAllStock() {
        List<Stock> stocks = stockServiceImpl.retrieveAllStock();
        Assertions.assertEquals(0,stocks.size());
        System.out.println("Retrieve All stocks");
    }
}