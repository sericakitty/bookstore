package hh.sof03.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long categoryId;
  private String name;

  public Category(String name) {
    super();
    this.name = name;
  }

  public Category() {}

  public Long getCategoryId() {
    return categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Category [categoryId=" + categoryId + ", name=" + name + "]";
  }

}
