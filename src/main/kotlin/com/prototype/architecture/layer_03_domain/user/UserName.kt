package com.prototype.architecture.layer_03_domain.user

import com.prototype.architecture.layer_03_domain.SingleValueObject
import javax.validation.constraints.NotBlank

data class UserName(@field:NotBlank override val value: String) : SingleValueObject<String>