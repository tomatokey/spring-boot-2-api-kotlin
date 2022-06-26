package com.prototype.framework.configuration.jdbc.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Transactional
annotation class TransactionalForUpd(
    @get:AliasFor(annotation = Transactional::class, attribute = "rollbackFor")
    val rollbackFor: Array<KClass<out Throwable>> = [Throwable::class]
)