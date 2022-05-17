package com.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBootアプリケーションクラス
 *
 * @see <a href="https://spring.pleiades.io/spring-boot/docs/current/reference/html/howto.html#howto.traditional-deployment.war">デプロイ可能な War ファイルを作成する</a>
 */
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
