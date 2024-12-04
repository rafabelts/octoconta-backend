package org.octoconta.octobackend.rest;

import jakarta.validation.Valid;
import org.octoconta.octobackend.dto.UserDTO;
import org.octoconta.octobackend.service.AuthService;
import org.octoconta.octobackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final AuthService authService;

    public UserResource(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        final Long newUserId = authService.create(userDTO);
        return new ResponseEntity<>(newUserId, HttpStatus.CREATED);
    }


}