package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest{
    @InjectMocks
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
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("07837423-095b-4451-a922-7049838bcbf6");
        product2.setProductName("Pirozhki");
        product2.setProductQuantity(3);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productRepository.create(product1);

        Product editedProduct1 = new Product();
        editedProduct1.setProductId(product1.getProductId());
        editedProduct1.setProductName("Keeki Andagi");
        editedProduct1.setProductQuantity(890);
        productRepository.edit(editedProduct1);

        assertEquals("Keeki Andagi", product1.getProductName());
        assertEquals(890, product1.getProductQuantity());
        assertNotEquals("Sata Andagi", product1.getProductName());
        assertNotEquals(101, product1.getProductQuantity());
    }

    @Test
    void testEditProductNotFound(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productRepository.create(product1);

        Product editedProduct1 = new Product();
        editedProduct1.setProductId("wrong id");
        editedProduct1.setProductName("Keeki Andagi");
        editedProduct1.setProductQuantity(890);

        assertThrows(NullPointerException.class, () -> {
            productRepository.edit(editedProduct1);
        });
    }

    @Test
    void testDeleteProductThenFindById(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("07837423-095b-4451-a922-7049838bcbf6");
        product2.setProductName("Pirozhki");
        product2.setProductQuantity(3);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());
        assertNull(productRepository.findById(product1.getProductId()));
        assertEquals(product2, productRepository.findById(product2.getProductId()));

        productRepository.delete(product2.getProductId());
        assertNull(productRepository.findById(product2.getProductId()));
    }

    @Test
    void testDeleteProductThenFindAll(){
        Product product1 = new Product();
        product1.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        product1.setProductName("Sata Andagi");
        product1.setProductQuantity(101);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("07837423-095b-4451-a922-7049838bcbf6");
        product2.setProductName("Pirozhki");
        product2.setProductQuantity(3);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productRepository.delete(product1.getProductId());
        assertTrue(productIterator.hasNext());
        productRepository.delete(product2.getProductId());
        assertFalse(productIterator.hasNext());
    }
}