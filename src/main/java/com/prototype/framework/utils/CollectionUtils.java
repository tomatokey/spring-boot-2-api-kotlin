package com.prototype.framework.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static <T> List<T> toList(Iterable<T> iterables) {
        final List<T> list = new ArrayList<>();
        iterables.forEach(list::add);
        return list;
    }

}
