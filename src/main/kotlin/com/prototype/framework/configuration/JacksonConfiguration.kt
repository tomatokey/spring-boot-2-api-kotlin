package com.prototype.framework.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Configuration
class JacksonConfiguration {

    @Bean
    fun createObjectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        return builder.build()
    }

    @Bean
    fun createObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val jtm = JavaTimeModule()
        jtm.addSerializer(
            LocalDateTime::class.java,
            LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
        )
        jtm.addDeserializer(
            LocalDateTime::class.java,
            LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))
        )
        val kotlinModule = KotlinModule.Builder().build()

        return Jackson2ObjectMapperBuilder() // 変換モジュールの設定
            .modules(ParameterNamesModule(), Jdk8Module(), jtm, kotlinModule) // 日付型のタイムゾーン設定
            .timeZone(TimeZone.getTimeZone("Asia/Tokyo")) // Date型専用のフォーマット設定
            .dateFormat(SimpleDateFormat(DATE_TIME_FORMAT)) // jsonのプロパティ名はスネーク型とする(変換詳細はSnakeCaseStrategyクラスを参照)
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE) // オブジェクトのプロパティがnullの場合、jsonに変換する際に含まれなくなります
            .serializationInclusion(JsonInclude.Include.NON_NULL) // 無効にする機能
            .featuresToDisable( // オブジェクトに存在しないプロパティがjsonに含まれている場合にエラーにする機能を無効化
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
    }

    companion object {
        const val DATE_TIME_FORMAT = "yyyy/MM/dd'T'HH:mm:ss"
    }

}