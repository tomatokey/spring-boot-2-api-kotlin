package com.tomatokey.prototype.configuration.converter;

import com.tomatokey.prototype.configuration.JdbcConfiguration;
import com.tomatokey.prototype.domain.user.UserId;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserId(値オブジェクト)用のコンバーター
 * Jdbcの変換には{@link JdbcConfiguration}にこのクラスを設定する必要があります
 * Jacksonの変換には{@link UserId}に@JsonValueと@JsonCreatorを設定する必要があります
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
                new ConvertiblePair(String.class, UserId.class), // MVC用
                new ConvertiblePair(Number.class, UserId.class), // JDBC(DB読込)用
                new ConvertiblePair(UserId.class, Number.class)  // JDBC(DB保存)用
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // JDK14から使用できるようになったinstanceofパターンマッチングを使用して判別
        if (source instanceof String o && targetType.getType() == UserId.class) {
            return new UserId(Integer.parseInt(o));
        }
        if (source instanceof Number o && targetType.getType() == UserId.class) {
            return new UserId(o.intValue());
        }
        if (source instanceof UserId o && targetType.getType() == Number.class) {
            return o.getValue();
        }

        return source;
    }

}