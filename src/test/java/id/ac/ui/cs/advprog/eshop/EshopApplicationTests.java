package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // Verifies Spring context is loading properly
    }

    @Test
    void mainMethodTest() {
        EshopApplication.main(new String[]{});
    }
}

