package hh.sof03.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.sof03.bookstore.domain.SignupForm;

@Controller
public class UserController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String addBook(Model model) {
    model.addAttribute("signupform", new SignupForm());
    return "signup";
  }

}
