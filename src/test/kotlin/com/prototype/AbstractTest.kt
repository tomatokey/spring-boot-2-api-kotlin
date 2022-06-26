package com.prototype

import com.fasterxml.jackson.databind.ObjectMapper
import com.prototype.framework.configuration.jdbc.annotation.DataSourceUpd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import java.io.BufferedReader
import javax.sql.DataSource

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(PrototypeTestConfiguration::class)
@EnableAspectJAutoProxy
abstract class AbstractTest {

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var resourceLoader: ResourceLoader

    @Autowired
    @DataSourceUpd
    protected lateinit var dataSource: DataSource

    protected val jdbcTemplate: JdbcTemplate
        get() = JdbcTemplate(dataSource)

    protected fun readResource(path: String): String {
        val resource = resourceLoader.getResource("classpath:${path}")
        return resource.inputStream.bufferedReader().use(BufferedReader::readText)
    }

    protected fun <T> readJsonResource(path: String, clazz: Class<T>): T {
        val json = readResource(path)
        return objectMapper.readValue(json, clazz)
    }

}