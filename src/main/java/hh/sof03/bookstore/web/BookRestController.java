package hh.sof03.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@CrossOrigin
@Controller
public class BookRestController {

  @Autowired
  private BookRepository bookRepository;
  
  @GetMapping("/books")
  public @ResponseBody List<Book> bookListRest() {
    return (List<Book>) bookRepository.findAll();
  }
}
