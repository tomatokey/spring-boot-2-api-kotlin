package com.prototype.framework.configuration.mvc.auth

import com.prototype.architecture.layer_03_domain.user.role.UserRoleType
import org.springframework.core.annotation.AliasFor

/**
 * [RestController]アノテーションが付与されたクラスまたは
 * そのクラス内のメソッドに付与すると認証の対象にすることができます
 */
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Authorize(
    @get:AliasFor("roles")
    vararg val value: UserRoleType = [],
    @get:AliasFor("value")
    val roles: Array<UserRoleType> = []
)