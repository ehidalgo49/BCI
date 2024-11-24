package com.bci.bciapi.service;

import com.bci.bciapi.adapter.repository.UserRepository;
import com.bci.bciapi.adapter.security.JwtTokenUtil;
import com.bci.bciapi.core.model.User;
import com.bci.bciapi.core.service.UserService;
import com.bci.bciapi.exception.InvalidDataException;
import com.bci.bciapi.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        userService = new UserService(userRepository, jwtTokenUtil);

        // Simular las propiedades inyectadas con Reflection
        java.lang.reflect.Field emailRegexField = UserService.class.getDeclaredField("emailRegex");
        emailRegexField.setAccessible(true);
        emailRegexField.set(userService, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        java.lang.reflect.Field passwordRegexField = UserService.class.getDeclaredField("passwordRegex");
        passwordRegexField.setAccessible(true);
        passwordRegexField.set(userService, "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$");
    }

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("test@colombia.com");
        user.setPassword("Password123!");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user));
    }

    @Test
    public void testRegisterUser_InvalidEmail() {
        User user = new User();
        user.setEmail("invalid-email");
        user.setPassword("Password123!");

        assertThrows(InvalidDataException.class, () -> userService.registerUser(user));
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        User user = new User();
        user.setEmail("test@colombia.com");
        user.setPassword("123");

        assertThrows(InvalidDataException.class, () -> userService.registerUser(user));
    }
}
