package com.prototype.architecture.layer_01_presentation.user;

import com.prototype.architecture.layer_01_presentation.Query;
import com.prototype.architecture.layer_03_domain.user.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class UserCreateQuery implements Query {

    @NotNull
    @Valid
    private UserName userName;

}
