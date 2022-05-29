package com.prototype.framework.configuration.jdbc.converter;

import com.prototype.architecture.layer_03_domain.SingleValueObject
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * ValueObjectを継承したクラスをEntityで使用するためのコンバーター
 */
class JdbcSingleValueObjectConverter : GenericConverter {

    override fun getConvertibleTypes(): MutableSet<ConvertiblePair>? {
        return listOf(
                // DB参照用
                ConvertiblePair(String::class.java, SingleValueObject::class.java),
                ConvertiblePair(Number::class.java, SingleValueObject::class.java),
                // DB更新用
                ConvertiblePair(SingleValueObject::class.java, String::class.java),
                ConvertiblePair(SingleValueObject::class.java, Number::class.java)
        ).toMutableSet()
    }

    override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
        val parameterizedType = targetType.type.genericInterfaces[0] as? ParameterizedType

        // DB参照用
        if (parameterizedType?.rawType == SingleValueObject::class.java) {
            val genericType: Type = parameterizedType.actualTypeArguments?.get(0) ?: throw IllegalAccessException()
            if (source is String && genericType == String::class.java) {
                return targetType.type.getConstructor(String::class.java).newInstance(source)
            }
            if (source is Number && genericType == Integer::class.java) {
                return targetType.type.getConstructor(Int::class.java).newInstance(source.toInt())
            }
        }

        // DB更新用
        if (source is SingleValueObject<*>) {
            return source.value
        }

        return source;
    }


}