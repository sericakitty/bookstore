package hh.sof03.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.sof03.bookstore.domain.BookRepository;


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
  
}
