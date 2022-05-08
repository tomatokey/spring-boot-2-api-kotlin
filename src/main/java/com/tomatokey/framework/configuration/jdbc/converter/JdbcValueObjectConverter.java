package com.tomatokey.framework.configuration.jdbc.converter;

import com.tomatokey.architecture.layer_03_domain.ValueObject;
import lombok.SneakyThrows;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ValueObjectを継承したクラスをEntityで使用するためのコンバーター
 */
public class JdbcValueObjectConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                // DB参照用
                new ConvertiblePair(String.class, ValueObject.class),
                new ConvertiblePair(Number.class, ValueObject.class),
                // DB更新用
                new ConvertiblePair(ValueObject.class, String.class),
                new ConvertiblePair(ValueObject.class, Number.class)
        };
        return new HashSet(List.of(pairs));
    }

    @SneakyThrows
    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // DB参照用
        if (targetType.getType().getSuperclass() == ValueObject.class) {
            final Type targetGenericType = ((ParameterizedType) targetType.getType().getGenericSuperclass()).getActualTypeArguments()[0];
            if (targetGenericType == String.class && source instanceof String o) {
                return targetType.getType().getDeclaredConstructor(String.class).newInstance(o);
            }
            if (targetGenericType == Integer.class && source instanceof Number o) {
                return targetType.getType().getDeclaredConstructor(Integer.class).newInstance(o.intValue());
            }
        }
        // DB更新用
        if (source instanceof ValueObject o) {
            return o.getValue();
        }

        return source;
    }

}