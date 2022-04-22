package com.tomatokey.prototype.configuration.converter.mvc;

import com.tomatokey.prototype.domain.models.values.id.RoleId;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleIdConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return true;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(String.class, RoleId.class)
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source instanceof String o) {
            return new RoleId(Long.parseLong(o));
        }

        return source;
    }

}