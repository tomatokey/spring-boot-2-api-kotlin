package com.tomatokey.framework.configuration.jdbc.converter;

import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.framework.configuration.jdbc.JdbcConfiguration;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserId(値オブジェクト)用のコンバーター
 * Jdbcの変換には{@link JdbcConfiguration}にこのクラスを設定する必要があります
 * Jacksonの変換には{@link UserId}に@JsonValueと@JsonCreatorを設定する必要があります
 */
public class UserIdConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(Number.class, UserId.class), // DB参照用 (Number -> UserId)
                new ConvertiblePair(UserId.class, Number.class)  // DB更新用 (UserId -> Number)
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        // JDK14から使用できるようになったinstanceofパターンマッチングを使用して判別
        if (source instanceof Number o && targetType.getType() == UserId.class) {
            return new UserId(o.intValue());
        }
        if (source instanceof UserId o && targetType.getType() == Number.class) {
            return o.getValue();
        }

        return source;
    }

}