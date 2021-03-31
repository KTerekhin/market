package ru.geekbrains.market.tests.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repositories.UserRepository;
import ru.geekbrains.market.services.UserService;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findByUsername() {
        User testUser = new User();
        testUser.setId(5L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@mail.ru");

        Mockito
                .doReturn(Optional.of(testUser))
                .when(userRepository)
                .findByUsername("testUser");

        User userFromService = userService.findByUsername("testUser").get();
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("testUser"));
        Assertions.assertEquals(testUser.getUsername(), userFromService.getUsername());
        Assertions.assertEquals(testUser.getEmail(), userFromService.getEmail());
    }
}