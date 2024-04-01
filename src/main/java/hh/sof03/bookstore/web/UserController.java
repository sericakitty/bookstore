package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import hh.sof03.bookstore.domain.SignupForm;
import hh.sof03.bookstore.domain.UserRepository;
import hh.sof03.bookstore.domain.User;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String addBook(Model model) {
    model.addAttribute("signupform", new SignupForm());
    return "signup";
  }

  @PostMapping("/saveuser")
  public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    String newUsername = signupForm.getUsername();
    String pwd = signupForm.getPassword();
    String pwdCheck = signupForm.getPasswordCheck();

    if (bindingResult.hasErrors()) { // if there are errors in the form
      return "signup";
    }

    if (!pwd.equals(pwdCheck)) { // check if password and passwordCheck are not the same
      bindingResult.rejectValue("passwordCheck", "error.signupForm", "Passwords do not match");
      return "signup";
    }
    
    if (userRepository.findByUsername(newUsername) != null) { // Check if username already exists
      bindingResult.rejectValue("username", "error.signupForm", "Username already exists");
      return "signup";
    }

    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
    String hashPwd = bc.encode(pwd);

    User newUser = new User();
    newUser.setPasswordHash(hashPwd);
    newUser.setUsername(newUsername);
    newUser.setRole("USER");

    userRepository.save(newUser);

    return "redirect:/login";
  }

}
