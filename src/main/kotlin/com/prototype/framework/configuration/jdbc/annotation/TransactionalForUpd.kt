package com.prototype.framework.configuration.jdbc.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.transaction.annotation.Transactional
import java.lang.annotation.Documented
import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Documented
@Transactional
annotation class TransactionalForUpd(
        @get:AliasFor(annotation = Transactional::class, attribute = "rollbackFor")
        val rollbackFor: Array<KClass<out Throwable>> = [Throwable::class]
)