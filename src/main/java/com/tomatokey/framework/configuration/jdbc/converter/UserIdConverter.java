package com.tomatokey.framework.configuration.jdbc.converter;

import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.framework.configuration.jdbc.JdbcConfiguration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserId(値オブジェクト)用のコンバーター
 * Jdbcの変換には{@link JdbcConfiguration}にこのクラスを設定する必要があります
 */
@Component
public class UserIdConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(String.class, UserId.class), // @PathVariable用 (String -> UserId)
                new ConvertiblePair(Number.class, UserId.class), // DB参照用 (Number -> UserId)
                new ConvertiblePair(UserId.class, Number.class)  // DB更新用 (UserId -> Number)
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // JDK14から使用できるようになったinstanceofパターンマッチングを使用して判別
        if (source instanceof String o) {
            return new UserId(Integer.parseInt(o));
        }
        if (source instanceof Number o) {
            return new UserId(o.intValue());
        }
        if (source instanceof UserId o) {
            return o.getValue();
        }

        return source;
    }

}