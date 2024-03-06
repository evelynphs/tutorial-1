package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private EshopApplication application;

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> application.main(new String[] {}));
    }

}
