package com.tomatokey.prototype.domain.models;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T getValue();
}
