package hh.sof03.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import hh.sof03.bookstore.domain.User;
import hh.sof03.bookstore.domain.UserRepository;


@DataJpaTest
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  public void createNewUser() {
    User user = new User("test", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
    userRepository.save(user);
    assertThat(user.getId()).isNotNull();
  }

  @Test
  public void deleteUser() {
    User user = new User("user2", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
    userRepository.save(user);
    User deletedUser = userRepository.findByUsername("user2");
    userRepository.delete(deletedUser);
    List<User> users = (List<User>) userRepository.findAll();
    assertEquals(2, users.size());
  }

  @Test
  public void findByUsernameShouldReturnUser() {
    User user = userRepository.findByUsername("user");
    assertThat(user).isNotNull();
    assertThat(user.getUsername()).isEqualTo("user");
  }

}