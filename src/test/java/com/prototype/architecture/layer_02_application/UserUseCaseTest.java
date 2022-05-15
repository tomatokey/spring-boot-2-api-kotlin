package com.prototype.architecture.layer_02_application;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserUseCaseTest {

    @Autowired
    private UserUseCase userUseCase;

    @Test
    void create() {
        final UserEntity input = UserEntity.of(new UserName("test"));
        final UserEntity output = userUseCase.create(input);
        assertEquals("test", output.getUserName().getValue());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

}