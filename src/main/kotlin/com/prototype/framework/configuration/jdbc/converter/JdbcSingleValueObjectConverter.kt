package com.prototype.framework.configuration.jdbc.converter;

import com.prototype.architecture.layer_03_domain.SingleValueObject
import com.prototype.framework.extension.toSingleValueObject
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair

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
        // DB更新用
        if (source is SingleValueObject<*>) {
            if (source.isInvalid) {
                throw IllegalArgumentException("${source.javaClass}:${source.value} is invalid value")
            }
            return source.value
        }

        // DB参照用
        return source?.toSingleValueObject(sourceType, targetType)
    }

}