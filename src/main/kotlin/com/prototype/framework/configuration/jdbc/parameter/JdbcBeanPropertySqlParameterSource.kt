package com.prototype.framework.configuration.jdbc.parameter

import com.prototype.architecture.layer_03_domain.Entity
import com.prototype.architecture.layer_03_domain.SingleValueObject
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource

class JdbcBeanPropertySqlParameterSource(entity: Entity) : BeanPropertySqlParameterSource(entity) {

    override fun getValue(paramName: String): Any? {
        val value = super.getValue(paramName)
        if (value is SingleValueObject<*>) {
            return value.value
        }
        return value
    }

}