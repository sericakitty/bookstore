package hh.sof03.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.bookstore.domain.Book;


@Controller
public class BookController {

  @Autowired
  private BookRepository repository;
  
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
      return "addbook";
  }

  @PostMapping("/save")
  public String saveBook(Book book) {
    repository.save(book);
    return "redirect:booklist";
  }
  
  @GetMapping("/delete/{id}")
  public String deleteBook(@PathVariable("id") Long id, Model model) {
      repository.deleteById(id);
      return "redirect:../booklist";
}
  
}
