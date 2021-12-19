package com.micro.store;

import com.micro.store.entity.Category;
import com.micro.store.entity.Product;
import com.micro.store.repository.ProductRepository;
import com.micro.store.service.ProductService;
import com.micro.store.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServicesMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product manga = Product.builder()
                .id(1L)
                .name("Stone Ocean")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("150000"))
                .stock(Double.parseDouble("6"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(manga));

        Mockito.when(productRepository.save(manga)).thenReturn(manga);


    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("Stone Ocean");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newSock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newSock.getStock()).isEqualTo(14);
    }
}
