package hh.sof03.bookstore.smoke;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof03.bookstore.web.BookController;

@SpringBootTest
public class BookControllerSmokeTest {

    @Autowired
    private BookController bookController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }
}
