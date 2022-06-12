package com.prototype.framework.extension

import com.prototype.architecture.layer_03_domain.SingleValueObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.TypeDescriptor
import java.lang.reflect.ParameterizedType

val Any.log: Logger
    get() = LoggerFactory.getLogger(this.javaClass)

fun Any.toSingleValueObject(sourceType: TypeDescriptor, targetType: TypeDescriptor): Any {
    val targetGenericType = targetType.type.genericInterfaces
            .map { it as? ParameterizedType }
            .firstOrNull { it?.rawType == SingleValueObject::class.java }
            ?.actualTypeArguments?.getOrNull(0)

    if (this is String) {
        return when (targetGenericType) {
            String::class.javaObjectType -> targetType.type.getConstructor(String::class.java).newInstance(this)
            Int::class.javaObjectType -> targetType.type.getConstructor(Int::class.java).newInstance(this.toInt())
            Long::class.javaObjectType -> targetType.type.getConstructor(Long::class.java).newInstance(this.toLong())
            Float::class.javaObjectType -> targetType.type.getConstructor(Float::class.java).newInstance(this.toFloat())
            Double::class.javaObjectType -> targetType.type.getConstructor(Double::class.java).newInstance(this.toDouble())
            else -> throw IllegalStateException("String型から${targetGenericType}への変換処理を追加してください")
        }
    }
    if (this is Number) {
        return when (targetGenericType) {
            Int::class.javaObjectType -> targetType.type.getConstructor(Int::class.java).newInstance(this.toInt())
            Long::class.javaObjectType -> targetType.type.getConstructor(Long::class.java).newInstance(this.toLong())
            Float::class.javaObjectType -> targetType.type.getConstructor(Float::class.java).newInstance(this.toFloat())
            Double::class.javaObjectType -> targetType.type.getConstructor(Double::class.java).newInstance(this.toDouble())
            else -> throw IllegalStateException("Number型から${targetGenericType}への変換処理を追加してください")
        }
    }

    throw IllegalStateException("${sourceType}から${targetGenericType}への変換処理を追加してください")
}