package com.prototype.architecture.layer_01_presentation.user;

import com.prototype.architecture.layer_02_application.user.create.CreateUserInput;
import com.prototype.architecture.layer_02_application.user.create.CreateUserOutput;
import com.prototype.architecture.layer_02_application.user.create.CreateUserUseCase;
import com.prototype.architecture.layer_02_application.user.find.FindAllUserUseCase;
import com.prototype.architecture.layer_02_application.user.find.FindByIdUserUseCase;
import com.prototype.architecture.layer_02_application.user.find.FindUserOutput;
import com.prototype.architecture.layer_03_domain.auth.AuthUser;
import com.prototype.architecture.layer_03_domain.auth.GetAuthUserService;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.framework.configuration.mvc.auth.Authorize;
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

    private final CreateUserUseCase createUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final FindByIdUserUseCase findByIdUserUseCase;
    private final GetAuthUserService authService;

    @PostMapping
    public UserResponse create(@RequestBody @Validated UserCreateQuery query) {
        final CreateUserInput input = new CreateUserInput(query.getUserName());
        final CreateUserOutput output = createUserUseCase.apply(input);
        return UserResponse.of(output);
    }

    @Authorize({UserRoleType.ADMIN})
    @GetMapping
    public List<UserResponse> findAll() {
        final List<FindUserOutput> users = findAllUserUseCase.get();
        return users.stream().map(UserResponse::of).collect(Collectors.toList());
    }

    @Authorize
    @GetMapping("findByToken")
    public ResponseEntity<UserResponse> findByToken() {
        final AuthUser authUser = authService.get();
        return ResponseEntity.of(findByIdUserUseCase.apply(authUser.userId()).map(UserResponse::of));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> findByPathId(@PathVariable("userId") UserId userId) {
        return ResponseEntity.of(findByIdUserUseCase.apply(userId).map(UserResponse::of));
    }

    @GetMapping( "findById")
    public ResponseEntity<UserResponse> findById(@RequestParam("userId") UserId userId) {
        return ResponseEntity.of(findByIdUserUseCase.apply(userId).map(UserResponse::of));
    }

}
