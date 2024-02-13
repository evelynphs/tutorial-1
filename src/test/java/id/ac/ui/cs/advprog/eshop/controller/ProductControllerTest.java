package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.controller.ProductController;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest{
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Mock
    Model model;

    @BeforeEach
    void setUp(){
    }

    @Test
    void createProductPageTest(){
        assertEquals("createProduct", productController.createProductPage(model));
    }

    @Test
    void createProductPostTest(){
        Product dummyProduct = new Product();
        dummyProduct.setProductName("dummy");
        dummyProduct.setProductQuantity(10);

        assertEquals("redirect:list", productController.createProductPost(dummyProduct, model));
    }

    @Test
    void editProductPageTest(){
        Product dummyProduct = new Product();
        dummyProduct.setProductName("dummy");
        dummyProduct.setProductQuantity(10);

        String dummyId = dummyProduct.getProductId();
        when(productService.findById(dummyProduct.getProductId())).thenReturn(dummyProduct);

        assertEquals("editProduct", productController.editProductPage(dummyId, model));
    }

    @Test
    void editProductPostTest(){
        Product dummy = new Product();
        dummy.setProductName("dummy");
        dummy.setProductQuantity(10);

        assertEquals("redirect:/product/list", productController.editProductPost(dummy, model));
    }

    @Test
    void deleteProductTest(){
        Product dummy = new Product();
        dummy.setProductName("dummy");
        dummy.setProductQuantity(10);

        String dummyId = dummy.getProductId();

        assertEquals("redirect:/product/list", productController.deleteProduct(dummyId));
    }

    @Test
    void productListPageTest(){
        when(productService.findAll()).thenReturn(new ArrayList<>());

        assertEquals("productList", productController.productListPage(model));
    }
}