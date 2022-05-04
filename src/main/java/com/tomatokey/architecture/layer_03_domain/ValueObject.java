package com.tomatokey.architecture.layer_03_domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tomatokey.architecture.layer_03_domain.user.UserId;

/**
 * 値オブジェクト用の基底クラス
 *
 * @param <T> value
 */
public abstract class ValueObject<T extends Comparable<T>> {

    /**
     * {@link JsonValue}を付与することによって、例えば
     * {@code {"user_id": {value: 1}}}だったものが
     * {@code {"user_id": 1}}のようにシリアライズされます
     */
    @JsonValue
    private final T value;

    /**
     * {@link JsonCreator}を付与することによって、例えば
     * {@code {"user_id": 1}}のJsonが
     * {@link UserId}オブジェクトにデシリアライズされます
     */
    @JsonCreator
    protected ValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
