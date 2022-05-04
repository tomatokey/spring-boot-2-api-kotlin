package com.tomatokey.framework.configuration.jdbc.converter;

import com.tomatokey.architecture.layer_03_domain.user.UserName;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserNameConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(String.class, UserName.class),
                new ConvertiblePair(UserName.class, String.class)
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source instanceof String o) {
            return new UserName(o);
        }
        if (source instanceof UserName o) {
            return o.getValue();
        }

        return source;
    }

}