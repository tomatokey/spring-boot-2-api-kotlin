package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_02_application.UserUseCase;
import com.tomatokey.architecture.layer_03_domain.user.UserEntity;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public UserResponse create(@RequestBody @Validated UserCreateQuery query) {
        final UserEntity userInput = UserEntity.of(query.getUserName());
        final UserEntity userOutput = userUseCase.create(userInput);
        return UserResponse.of(userOutput);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        final List<UserEntity> users = userUseCase.findAll();
        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{userId}")
    public Optional<UserResponse> findById(@PathVariable("userId") UserId userId) {
        return userUseCase.findById(userId).map(UserResponse::of);
    }

}
