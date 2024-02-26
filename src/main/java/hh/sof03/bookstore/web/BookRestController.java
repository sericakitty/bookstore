package hh.sof03.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@CrossOrigin
@RestController
public class BookRestController {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;
  
  @GetMapping("/books")
  public List<Book> bookListRest() {
    return (List<Book>) bookRepository.findAll();
  }

  @GetMapping("/books/{id}")
  public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
    return bookRepository.findById(bookId);
  }

  @GetMapping("/books/search/findByTitle?title={title}")
  public Book findBookByTitleRest(@PathVariable("title") String title) {
    List <Book> books = bookRepository.findByTitle(title);

    if (books.isEmpty()) {
      throw new IllegalArgumentException("Book not found");
    }
    
    return books.get(0);

  }

  @PostMapping("/books")
  public Book saveBookRest(@RequestBody Book bookObject) {
    Book book = new Book();
    book.setTitle(bookObject.getTitle());
    book.setAuthor(bookObject.getAuthor());
    book.setPublicationYear(bookObject.getPublicationYear());
    book.setIsbn(bookObject.getIsbn());
    book.setPrice(bookObject.getPrice());
    Category category = categoryRepository.findByName(bookObject.getCategory().getName());
    book.setCategory(category);
    bookRepository.save(book);

    return book;
  }

  @PostMapping("/books/{id}")
  public Optional<Book> updateBookRest(@PathVariable("id") Long bookId, @RequestBody Book bookObject) {
    Optional<Book> book = bookRepository.findById(bookId);
    if (book.isPresent()) {

      if (bookObject.getTitle() != null) {
        book.get().setTitle(bookObject.getTitle());
      }

      if (bookObject.getAuthor() != null) {
        book.get().setAuthor(bookObject.getAuthor());
      }

      if (bookObject.getPublicationYear() != 0){
        book.get().setPublicationYear(bookObject.getPublicationYear());
      }

      if (bookObject.getIsbn() != null) {
        book.get().setIsbn(bookObject.getIsbn());
      }

      if (bookObject.getPrice() != 0) {
        book.get().setPrice(bookObject.getPrice());
      }

      if (bookObject.getCategory() != null) {
        Category category = categoryRepository.findByName(bookObject.getCategory().getName());
        book.get().setCategory(category);
      }

      bookRepository.save(book.get());
    }
    
    return book;
  }

  @DeleteMapping("/books/{id}")
  public void deleteBookRest(@PathVariable("id") Long bookId) {
    Book deletedBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    bookRepository.deleteById(deletedBook.getId());
  }
  

}
