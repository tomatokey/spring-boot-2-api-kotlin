package com.tomatokey.prototype.presentation.controller;

import com.tomatokey.prototype.application.UserUseCase;
import com.tomatokey.prototype.domain.constant.RoleType;
import com.tomatokey.prototype.domain.models.entities.User;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import com.tomatokey.prototype.infrastructure.db.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userUseCase.create(user, RoleType.VIEWER, RoleType.EDITOR);
    }

    @GetMapping
    public Iterable<User> get() {
        return userRepository.findAll();
    }

    @GetMapping("{userId}")
    public Optional<User> get(@PathVariable("userId") UserId userId) {
        return userRepository.findById(userId);
    }

    @GetMapping("/findById")
    public Optional<User> findById(@RequestParam("userId") UserId userId) {
        return userRepository.findById(userId);
    }


}
