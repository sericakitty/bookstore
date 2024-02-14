package hh.sof03.bookstore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
  
  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
