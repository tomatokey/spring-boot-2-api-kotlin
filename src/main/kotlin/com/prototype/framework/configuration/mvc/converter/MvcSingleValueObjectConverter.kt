package com.prototype.framework.configuration.mvc.converter;

import com.prototype.architecture.layer_03_domain.SingleValueObject
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * ValueObjectを継承したクラスを
 * {@link RequestParam}や{@link PathVariable}で使用するためのコンバーター
 */
@Component
class MvcSingleValueObjectConverter : GenericConverter {

    override fun getConvertibleTypes(): MutableSet<ConvertiblePair>? {
        return listOf(
                ConvertiblePair(String::class.java, SingleValueObject::class.java),
                ConvertiblePair(Number::class.java, SingleValueObject::class.java)
        ).toMutableSet()
    }

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        val parameterizedType = targetType.type.genericInterfaces[0] as? ParameterizedType
        if (source is String && parameterizedType?.rawType == SingleValueObject::class.java) {
            val genericType: Type = parameterizedType.actualTypeArguments?.get(0) ?: throw IllegalAccessException()
            if (genericType == String::class.java) {
                return targetType.type.getConstructor(String::class.java).newInstance(source)
            }
            if (genericType == Integer::class.java) {
                return targetType.type.getConstructor(Int::class.java).newInstance(source.toInt())
            }
        }

        return source;
    }

}