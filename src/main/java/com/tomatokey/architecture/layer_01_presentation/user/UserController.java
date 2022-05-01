package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_02_application.UserUseCase;
import com.tomatokey.architecture.layer_03_domain.user.User;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public UserResource create(@RequestBody UserCreateQuery query) {
        final User userInput = User.of(query.getUserName());
        final User userOutput = userUseCase.create(userInput);
        return UserResource.builder()
                .userId(userOutput.getUserId())
                .userName(userOutput.getUserName())
                .build();
    }

    @GetMapping
    public List<UserResource> findAll() {
        final List<User> users = userUseCase.findAll();
        return users.stream()
                .map(user -> UserResource.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{userId}")
    public Optional<User> findById(@PathVariable("userId") UserId userId) {
        return userUseCase.findById(userId);
    }

}
