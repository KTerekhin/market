package ru.geekbrains.market.tests.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repositories.UserRepository;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByNameTest() {
        Optional<User> testUser = userRepository.findByUsername("user");
        Assertions.assertEquals("user", testUser.get().getUsername());
        Assertions.assertEquals("user_johnson@gmail.com", testUser.get().getEmail());
    }
}
