package com.prototype.framework.configuration.mvc.auth

/**
 * [RestController]アノテーションが付与されたクラス内の
 * メソッドに付与すると認証の対象外にすることができます
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NonAuthorize 