package com.prototype

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

/**
 * SpringBootアプリケーションクラス
 *
 * @see [デプロイ可能な War ファイルを作成する](https://spring.pleiades.io/spring-boot/docs/current/reference/html/howto.html.howto.traditional-deployment.war)
 */
@SpringBootApplication
class MyApplication : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(MyApplication::class.java)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MyApplication::class.java, *args)
        }
    }
}