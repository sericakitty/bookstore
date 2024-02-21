package hh.sof03.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.CategoryRepository;
import hh.sof03.bookstore.domain.Book;

@Controller
public class BookController {

  @Autowired
  private BookRepository repository;

  @Autowired
  private CategoryRepository categoryRepository;
  
  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @GetMapping("/booklist")
  public String getAllBooks(Model model) {
      model.addAttribute("books", repository.findAll());
      return "booklist";
  }

  @GetMapping("/add")
  public String getAddBookForm(Model model) {
      model.addAttribute("book", new Book());
      model.addAttribute("categories", categoryRepository.findAll());
      return "addbook";
  }

  @PostMapping("/save")
  public String saveBook(Book book) {
    repository.save(book);
    return "redirect:/booklist";
  }

  @GetMapping("/edit/{id}")
  public String getEditBookForm(@PathVariable("id") Long id, Model model) {
    Book updatedBook = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    model.addAttribute("updatedBook", updatedBook);
    return "editbook";
  }

  @PostMapping("/update/{id}")
  public String updateBook(@PathVariable("id") Long id, @ModelAttribute("updatedBook") Book book) {
      Book updatedBook = repository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
      updatedBook.setTitle(book.getTitle());
      updatedBook.setAuthor(book.getAuthor());
      updatedBook.setPublicationYear(book.getPublicationYear());
      updatedBook.setIsbn(book.getIsbn());
      repository.save(updatedBook);

      return "redirect:/booklist";
  }
  
  @GetMapping("/delete/{id}")
  public String deleteBook(@PathVariable("id") Long id, Model model) {
      repository.deleteById(id);
      return "redirect:/booklist";
}
  
}
