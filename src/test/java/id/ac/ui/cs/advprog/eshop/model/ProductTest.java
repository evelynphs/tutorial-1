package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

class ProductTest{
    Product product;

    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setProductId("2b0c5fcf-1198-474c-bd1e-f0713131b326");
        this.product.setProductName("Sata Andagi");
        this.product.setProductQuantity(101);
    }

    @Test
    void testGetProductId(){
        assertEquals("2b0c5fcf-1198-474c-bd1e-f0713131b326", this.product.getProductId());
    }

    @Test
    void testGetProductName(){
        assertEquals("Sata Andagi", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity(){
        assertEquals(101, this.product.getProductQuantity());
    }

    @Test
    void testInvalidQuantityInput(){
        assertThrows(MethodArgumentNotValidException.class, () -> {
            this.product.setProductQuantity(Integer.parseInt("yey"));
        });
    }
}