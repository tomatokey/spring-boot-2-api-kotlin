package com.prototype.architecture.layer_03_domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

/**
 * Entityの基底クラス
 * 基底クラスは@JsonSerializeをつけることでJsonに変換されます
 */
abstract class Entity {
    @CreatedDate
    @Column(REGISTER_TIME)
    @JsonSerialize
    lateinit var registerTime: LocalDateTime

    @LastModifiedDate
    @Column(UPDATE_TIME)
    @JsonSerialize
    lateinit var updateTime: LocalDateTime

    fun applyForCreate(): Entity {
        return this.apply {
            registerTime = LocalDateTime.now()
            updateTime = LocalDateTime.now()
        }
    }

    fun applyForUpdate(): Entity {
        return this.apply {
            updateTime = LocalDateTime.now()
        }
    }

    companion object {
        const val REGISTER_TIME = "register_time"
        const val UPDATE_TIME = "update_time"
    }
}