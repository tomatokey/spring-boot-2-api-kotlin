package com.tomatokey.framework.configuration.mvc.auth;

import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link RestController}アノテーションが付与されたクラスまたは
 * そのクラス内のメソッドに付与すると認証の対象にすることができます
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {

    @AliasFor("roles")
    UserRoleType[] value() default {};

    /**
     * アクセスを許可する権限を設定します
     *
     * @return
     */
    @AliasFor("value")
    UserRoleType[] roles() default {};

}
