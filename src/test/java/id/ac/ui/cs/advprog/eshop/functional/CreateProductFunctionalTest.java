package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import javax.lang.model.element.Element;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest{

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/create");
        String pageTitle = driver.getTitle();

        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void pageHeader_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/create");
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();

        assertEquals("Create New Product", welcomeMessage);
    }

    @Test
    void productCreation_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl + "/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys("NAVI");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys("23");

        driver.findElement(By.tagName("button")).click();

        List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
        assertEquals(2, tableRows.size());

        int rowOrder = 0;

        for(WebElement row : tableRows){
            if(rowOrder == 1) {
                List<WebElement> tableColumns = row.findElements(By.tagName("td"));

                int columnOrder = 0;

                for (WebElement column : tableColumns) {
                    switch (columnOrder) {
                        case 0:
                            assertEquals("NAVI", column.getText());
                            break;
                        case 1:
                            assertEquals("23", column.getText());
                            break;
                        default:
                            break;
                    }
                    columnOrder++;
                }
            }
            rowOrder++;
        }
    }
}