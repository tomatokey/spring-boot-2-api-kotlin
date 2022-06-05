package com.prototype.architecture.layer_03_domain.user

import com.prototype.architecture.layer_03_domain.SingleValueObject

data class UserId(override val value: Int) : SingleValueObject<Int> {
    override val isInvalid: Boolean
        get() = value <= 0
}