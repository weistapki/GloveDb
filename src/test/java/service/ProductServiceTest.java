package service;

import com.example.glovedb.dto.ProductDto;
import com.example.glovedb.entity.Product;
import com.example.glovedb.repository.ProductRepository;
import com.example.glovedb.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void addToOrderTest () {

        ProductDto productDto = ProductDto.builder().id(1).name("Tomato").cost(10).build();

        Product savedProduct = Product.builder().id(1).name("Tomato").cost(10).orderId(1).build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDto result = productService.addToOrder(productDto, 1);

        verify(productRepository).save(any(Product.class));

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Tomato", result.getName());
        Assertions.assertEquals(10, result.getCost());
    }
}
