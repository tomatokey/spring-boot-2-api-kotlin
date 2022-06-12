package com.prototype.framework.extension

import com.prototype.architecture.layer_03_domain.SingleValueObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.TypeDescriptor
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.primaryConstructor

val Any.log: Logger
    get() = LoggerFactory.getLogger(this.javaClass)

fun Any.toSingleValueObject(sourceType: TypeDescriptor, targetType: TypeDescriptor): Any {
    val targetKClass = targetType.type.kotlin
    val targetGenericType = targetKClass.supertypes
            .firstOrNull { it.classifier == SingleValueObject::class }
            ?.arguments?.getOrNull(0)?.type?.classifier
            ?: throw IllegalStateException("${targetKClass}はSingleValueObjectを継承していません")
    val targetConstructor = targetKClass.primaryConstructor ?: throw IllegalStateException("${targetKClass}にコンストラクタが存在しません")

    if (this is String) {
        return when (targetGenericType) {
            String::class -> targetConstructor.call(this)
            Int::class -> targetConstructor.call(this.toInt())
            Long::class -> targetConstructor.call(this.toLong())
            Float::class -> targetConstructor.call(this.toFloat())
            Double::class -> targetConstructor.call(this.toDouble())
            else -> throw IllegalStateException("String型から${targetGenericType}への変換処理を追加してください")
        }
    }
    if (this is Number) {
        return when (targetGenericType) {
            Int::class -> targetConstructor.call(this.toInt())
            Long::class -> targetConstructor.call(this.toLong())
            Float::class -> targetConstructor.call(this.toFloat())
            Double::class -> targetConstructor.call(this.toDouble())
            else -> throw IllegalStateException("Number型から${targetGenericType}への変換処理を追加してください")
        }
    }

    throw IllegalStateException("${sourceType}から${targetGenericType}への変換処理を追加してください")
}