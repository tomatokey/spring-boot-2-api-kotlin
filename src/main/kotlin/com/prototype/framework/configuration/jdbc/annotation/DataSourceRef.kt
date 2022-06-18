package com.prototype.framework.configuration.jdbc.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited


@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Inherited
@Qualifier
annotation class DataSourceRef
