package com.tomatokey.prototype.domain.models.values.id;

import java.io.Serializable;

public interface ID<T> extends Serializable {
    T getId();
}
