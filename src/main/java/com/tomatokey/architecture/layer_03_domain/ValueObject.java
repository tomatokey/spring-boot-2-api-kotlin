package com.tomatokey.architecture.layer_03_domain;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T getValue();
}
