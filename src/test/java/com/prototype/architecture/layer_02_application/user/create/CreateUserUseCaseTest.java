package com.prototype.architecture.layer_02_application.user.create;

import com.prototype.architecture.layer_03_domain.user.UserName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreateUserUseCaseTest {

    @Autowired
    private CreateUserUseCase useCase;

    @Test
    void apply() {
        final CreateUserInput input = new CreateUserInput(new UserName("test"));
        final CreateUserOutput output = useCase.apply(input);
        assertEquals("test", output.userName().getValue());
    }

}