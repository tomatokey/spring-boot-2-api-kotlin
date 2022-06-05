package com.prototype.architecture.layer_03_domain

import com.fasterxml.jackson.annotation.JsonValue
import java.util.*
import javax.validation.constraints.NotNull

/**
 * 値が1つだけのバリューオブジェクト用インターフェース
 *
 * @param <T> value
 */
interface SingleValueObject<T : Comparable<T>> {

    val value: @NotNull T
        @JsonValue
        get() = value

    /**
     * 値が不正かどうか判定します
     * overrideして変更してください
     *
     * @return
     */
    val isInvalid: Boolean
        get() = false

}