package com.pedroporto.todosimple.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedroporto.todosimple.models.User;
import com.pedroporto.todosimple.models.User.CreateUser;
import com.pedroporto.todosimple.models.User.UpdateUser;
import com.pedroporto.todosimple.services.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User user) {
        this.userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody User user) {
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
