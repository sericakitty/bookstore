package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@CrossOrigin
@RestController
public class CategoryRestController {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping("/categories")
  public Iterable<Category> categoryListRest() {
    return categoryRepository.findAll();
  }

  @PostMapping("/categories")
  public Category saveCategoryRest(@RequestBody Category category) {
    
    Category categoryExists = categoryRepository.findByName(category.getName());
    if (categoryExists != null) {
      throw new IllegalArgumentException("Category already exists");
    }

    return categoryRepository.save(category);
  }

}
