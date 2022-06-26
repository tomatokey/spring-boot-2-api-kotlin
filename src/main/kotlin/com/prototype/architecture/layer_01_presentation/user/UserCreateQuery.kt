package com.prototype.architecture.layer_01_presentation.user

import com.prototype.architecture.layer_01_presentation.Query
import com.prototype.architecture.layer_03_domain.user.UserName
import javax.validation.Valid
import javax.validation.constraints.NotNull

data class UserCreateQuery(
    @field:Valid
    @field:NotNull
    val userName: UserName
) : Query