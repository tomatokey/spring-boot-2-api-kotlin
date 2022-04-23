package com.tomatokey.prototype.presentation.controller;

import com.tomatokey.prototype.application.UserUseCase;
import com.tomatokey.prototype.domain.user.User;
import com.tomatokey.prototype.domain.user.UserId;
import com.tomatokey.prototype.domain.user.UserRepository;
import com.tomatokey.prototype.domain.userrole.UserRoleRepository;
import com.tomatokey.prototype.domain.userrole.UserRoleType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userUseCase.create(user, UserRoleType.VIEWER, UserRoleType.EDITOR);
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
