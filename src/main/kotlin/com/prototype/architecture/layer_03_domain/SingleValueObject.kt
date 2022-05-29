package com.prototype.architecture.layer_03_domain

import com.fasterxml.jackson.annotation.JsonValue
import com.prototype.framework.utils.ObjectUtils
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
     *
     * より詳細な判定が必要な場合は@Overrideして変更してください
     *
     * @return
     */
    val isInvalid: Boolean
        get() = ObjectUtils.isEmpty(value)

}