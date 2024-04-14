package hh.sof03.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateBook() {
        Category category = new Category("Literature");
        categoryRepository.save(category);
        Book book = new Book("Moby Dick", "Herman Melville", 1851, "9780143105954", 15.99, category);
        bookRepository.save(book);

        assertThat(bookRepository.findById(book.getId())).isNotEmpty();
    }

    @Test
    public void testDeleteBook() {
        Category category = new Category("Literature");
        category = categoryRepository.save(category);
        Book book = new Book("Moby Dick", "Herman Melville", 1851, "9780143105954", 15.99, category);
        book = bookRepository.save(book);

        bookRepository.delete(book);
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }

    @Test
    public void testFindByTitle() {
      Category category = categoryRepository.save(new Category("Fiction"));
      Book book = new Book("1984", "George Orwell", 1949, "123456789", 15.99, category);
      bookRepository.save(book);
      Book expectedBook = bookRepository.findByTitle("1984").get(0);
      assertThat(expectedBook).isNotNull();
    }
}
