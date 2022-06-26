package com.prototype.architecture.layer_01_presentation.user

import com.prototype.AbstractTest
import com.prototype.ResourcePath
import com.prototype.SqlForUpd
import com.prototype.Sqls
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get

internal class UserControllerTest : AbstractTest() {

    @SqlForUpd(
        scripts = [
            Sqls.user_tのレコードを全て削除,
            Sqls.user_tに1件レコードを追加
        ]
    )
    @Test
    fun findById() {
        mockMvc.get("/users/findById") {
            param("userId", "1")
        }.andExpect {
            status { isOk() }
            content { json(readResource(ResourcePath.JSON.findByIdで1を指定した場合の期待値)) }
        }
    }

}