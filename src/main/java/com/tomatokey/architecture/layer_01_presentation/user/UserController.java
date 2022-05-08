package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_02_application.UserUseCase;
import com.tomatokey.architecture.layer_03_domain.auth.AuthService;
import com.tomatokey.architecture.layer_03_domain.auth.AuthUser;
import com.tomatokey.architecture.layer_03_domain.user.UserEntity;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
import com.tomatokey.framework.configuration.mvc.auth.Authorize;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping( "users")
public class UserController {

    private final UserUseCase userUseCase;
    private final AuthService authService;

    @PostMapping
    public UserResponse create(@RequestBody @Validated UserCreateQuery query) {
        final UserEntity userInput = UserEntity.of(query.getUserName());
        final UserEntity userOutput = userUseCase.create(userInput);
        return UserResponse.of(userOutput);
    }

    @Authorize({UserRoleType.ADMIN})
    @GetMapping
    public List<UserResponse> findAll() {
        final List<UserEntity> users = userUseCase.findAll();
        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    @Authorize
    @GetMapping("findByToken")
    public ResponseEntity<UserResponse> findByToken() {
        final AuthUser authUser = authService.getAuthUser();
        return ResponseEntity.of(userUseCase.findById(authUser.userId()).map(UserResponse::of));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> findByPathId(@PathVariable("userId") UserId userId) {
        return ResponseEntity.of(userUseCase.findById(userId).map(UserResponse::of));
    }

    @GetMapping( "findById")
    public ResponseEntity<UserResponse> findById(@RequestParam("userId") UserId userId) {
        return ResponseEntity.of(userUseCase.findById(userId).map(UserResponse::of));
    }

}
