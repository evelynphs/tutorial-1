package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest{
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){
    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product.setProductName("Sata Andagi");
        product.setProductQuantity(101);
        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        Product product2 = new Product();
        product2.setProductId("07837423-095b-4451-a922-7049838bcbf6");
        product2.setProductName("Pirozhki");
        product2.setProductQuantity(3);
        when(productRepository.create(product2)).thenReturn(product2);
        productService.create(product2);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2).iterator());
        List<Product> productList = productService.findAll();
        assertEquals(2, productList.size());
        Product savedProduct = productList.get(0);
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productList.get(1);
        assertEquals(product2.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testEditProduct(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        Product editedProduct1 = new Product();
        editedProduct1.setProductId(product1.getProductId());
        editedProduct1.setProductName("Keeki Andagi");
        editedProduct1.setProductQuantity(890);

        doAnswer(invocationOnMock -> {
            product1.setProductName(editedProduct1.getProductName());
            product1.setProductQuantity(editedProduct1.getProductQuantity());
            return null;
        }).when(productRepository).edit(editedProduct1);

        productService.edit(editedProduct1);

        assertEquals("Keeki Andagi", product1.getProductName());
        assertEquals(890, product1.getProductQuantity());
        assertNotEquals("Sata Andagi", product1.getProductName());
        assertNotEquals(101, product1.getProductQuantity());
    }

    @Test
    void testCreateProductThenFindById(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        when(productRepository.findById(product1.getProductId())).thenReturn(product1);
        Product foundProduct = productService.findById(product1.getProductId());

        assertEquals(product1.getProductId(), foundProduct.getProductId());
        assertEquals(product1.getProductName(), foundProduct.getProductName());
        assertEquals(product1.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testDeleteProductThenFindAll(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productService.create(product1);

        when(productRepository.findAll()).thenReturn(List.of(product1).iterator());
        List<Product> productList = productService.findAll();
        assertEquals(1, productList.size());

        doAnswer(invocationOnMock -> {
            productList.remove(product1);
            return null;
        }).when(productRepository).delete(product1.getProductId());

        productService.delete(product1.getProductId());
        assertTrue(productList.isEmpty());
    }
}