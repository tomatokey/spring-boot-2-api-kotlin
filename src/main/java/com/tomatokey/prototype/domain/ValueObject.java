package com.tomatokey.prototype.domain;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T getValue();
}
