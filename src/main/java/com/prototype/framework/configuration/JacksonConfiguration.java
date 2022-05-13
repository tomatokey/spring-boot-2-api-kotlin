package com.prototype.framework.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfiguration {

    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd'T'HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return createObjectMapperBuilder();
    }

    public Jackson2ObjectMapperBuilder createObjectMapperBuilder() {
        final JavaTimeModule jtm = new JavaTimeModule();
        jtm.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        jtm.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));

        return new Jackson2ObjectMapperBuilder()
                // 変換モジュールの設定
                .modules(new ParameterNamesModule(), new Jdk8Module(), jtm)
                // 日付型のタイムゾーン設定
                .timeZone(TimeZone.getTimeZone("Asia/Tokyo"))
                // Date型専用のフォーマット設定
                .dateFormat(new SimpleDateFormat(DATE_TIME_FORMAT))
                // jsonのプロパティ名はスネーク型とする(変換詳細はSnakeCaseStrategyクラスを参照)
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                // オブジェクトのプロパティがnullの場合、jsonに変換する際に含まれなくなります
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                // 無効にする機能
                .featuresToDisable(
                        // オブジェクトに存在しないプロパティがjsonに含まれている場合にエラーにする機能を無効化
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                );
    }

    public ObjectMapper createObjectMapper() {
        return createObjectMapperBuilder().build();
    }

}
