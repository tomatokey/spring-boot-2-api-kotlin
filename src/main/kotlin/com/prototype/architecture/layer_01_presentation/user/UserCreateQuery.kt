package com.prototype.architecture.layer_01_presentation.user

import com.prototype.architecture.layer_01_presentation.Query
import com.prototype.architecture.layer_03_domain.user.UserName
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class UserCreateQuery(
        val userName: @NotNull @Valid UserName
) : Query