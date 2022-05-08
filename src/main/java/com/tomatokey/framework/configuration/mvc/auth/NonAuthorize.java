package com.tomatokey.framework.configuration.mvc.auth;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link RestController}アノテーションが付与されたクラス内の
 * メソッドに付与すると認証の対象外にすることができます
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonAuthorize {
}
