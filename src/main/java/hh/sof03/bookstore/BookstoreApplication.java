package hh.sof03.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(BookRepository repository) {
    return (args) -> {
      Book book1 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, "0-395-19395-8", 19.99);
      Book book2 = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, "0-7475-3269-9", 15.99);
      Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "0-395-19395-8", 19.99);
    
      repository.save(book1);
      repository.save(book2);
      repository.save(book3);
    };
  }

}
