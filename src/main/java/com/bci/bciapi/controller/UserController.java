package com.bci.bciapi.controller;

import com.bci.bciapi.core.model.User;
import com.bci.bciapi.core.port.UserPort;
import com.bci.bciapi.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserPort userPort;

    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Errores de validación en los datos enviados"));
        }

        try {
            User registeredUser = userPort.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("User already exists"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
        }
    }
}