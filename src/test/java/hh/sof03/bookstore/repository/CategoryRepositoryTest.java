package hh.sof03.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        Category category = new Category("Science Fiction");
        categoryRepository.save(category);

        assertThat(categoryRepository.findById(category.getCategoryid())).isNotEmpty();
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category("Science Fiction");
        category = categoryRepository.save(category);

        categoryRepository.delete(category);
        assertThat(categoryRepository.findById(category.getCategoryid())).isEmpty();
    }

    @Test
    public void testFindByName() {
        Category category = new Category("Science Fiction");
        categoryRepository.save(category);

        Category foundCategory = categoryRepository.findByName("Science Fiction");
        assertThat(foundCategory).isEqualTo(category);
    }
}

