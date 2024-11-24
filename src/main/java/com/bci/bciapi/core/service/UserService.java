package com.bci.bciapi.core.service;

import com.bci.bciapi.adapter.repository.UserRepository;
import com.bci.bciapi.adapter.security.JwtTokenUtil;
import com.bci.bciapi.core.model.User;
import com.bci.bciapi.core.port.UserPort;
import com.bci.bciapi.exception.UserAlreadyExistsException;
import com.bci.bciapi.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService implements UserPort {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${validation.email.regex}")
    private String emailRegex;

    @Value("${validation.password.regex}")
    private String passwordRegex;

    public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User registerUser(User user) {
        validateUserData(user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya está registrado");
        }

        user.setToken(jwtTokenUtil.generateToken(user.getEmail()));
        return userRepository.save(user);
    }

    private void validateUserData(User user) {
        if (!Pattern.matches(emailRegex, user.getEmail())) {
            throw new InvalidDataException("El formato del correo es inválido");
        }
        if (!Pattern.matches(passwordRegex, user.getPassword())) {
            throw new InvalidDataException("El formato de la contraseña es inválido");
        }
    }
}
