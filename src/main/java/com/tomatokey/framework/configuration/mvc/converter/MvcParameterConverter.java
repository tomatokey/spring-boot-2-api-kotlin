package com.tomatokey.framework.configuration.mvc.converter;

import com.tomatokey.architecture.layer_03_domain.user.UserId;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link RequestParam}や{@link PathVariable}用のコンバーター
 */
@Component
public class MvcParameterConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final ConvertiblePair[] pairs = new ConvertiblePair[] {
                new ConvertiblePair(String.class, UserId.class),
        };
        return new HashSet(List.of(pairs));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source instanceof String o) {
            if (targetType.getType() == UserId.class) {
                return new UserId(Integer.parseInt(o));
            }
        }

        return source;
    }

}