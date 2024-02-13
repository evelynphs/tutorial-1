package id.ac.ui.cs.advprog.eshop.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest{
    @InjectMocks
    HomeController homeController;

    @BeforeEach
    void setUp(){
    }

    @Test
    void showHomePageTest(){
        assertEquals("homePage", homeController.showHomePage());
    }
}