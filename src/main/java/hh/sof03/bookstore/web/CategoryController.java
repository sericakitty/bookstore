package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import hh.sof03.bookstore.domain.CategoryRepository;

import hh.sof03.bookstore.domain.Category;

@Controller
public class CategoryController {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping("/categorylist")
  public String getAllCategories(Model model) {
      model.addAttribute("categories", categoryRepository.findAll());
      return "categorylist";
  }

  @GetMapping("/addcategory")
  public String getAddCategoryForm(Model model) {
    model.addAttribute("category", new Category());
    return "addcategory";
  }

  @PostMapping("/savecategory")
  public String saveCategory(Category category) {
    categoryRepository.save(category);
    return "redirect:/categorylist";
  }
  
  
}
