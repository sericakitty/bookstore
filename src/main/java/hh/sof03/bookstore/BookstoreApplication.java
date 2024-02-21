package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

  private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
    return (args) -> {
      Category category1 = new Category("Fantasy");
      Category category2 = new Category("Children's literature");

      categoryRepository.save(category1);
      categoryRepository.save(category2);

      log.info("fetch all categories");
      for (Category category : categoryRepository.findAll()) {
        log.info(category.toString());
      }

      Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, "0-395-19395-8", 19.99, category1);
      Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "0-7475-3269-9", 15.99, category1);
      Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "0-395-19395-8", 19.99, category1);
      Book book4 = new Book("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 1950, "0-06-447104-7", 12.99, category2);

      bookRepository.save(book1);
      bookRepository.save(book2);
      bookRepository.save(book3);
      bookRepository.save(book4);

      log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
        log.info(book.toString());
			}

    };
  }

}
