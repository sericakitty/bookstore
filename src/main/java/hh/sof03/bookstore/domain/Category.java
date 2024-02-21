package hh.sof03.bookstore.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long categoryid;
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private List<Book> books;


  public Category(String name) {
    super();
    this.name = name;
  }

  public Category() {}

  public Long getCategoryid() {
    return categoryid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "Category [categoryid=" + categoryid + ", name=" + name + "]";
  }

}
