package com.prototype.framework.configuration.mvc.auth;

import com.prototype.architecture.layer_03_domain.auth.AuthUser
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.method.HandlerMethod
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.reflect.Method
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationHandlerInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod && isAuthorizeTarget(handler)) {
            val authUser = getAuthUser(request) ?: throw ResponseStatusException(HttpStatus.FORBIDDEN)
            // 認証
            authorize(handler, authUser)
            // 認証ユーザー情報をContextに設定
            SecurityContextHolder.getContext().authentication = authUser
        }

        return true
    }

    /**
     * 認証対象かどうか判定します
     *
     * @param handler
     * @return
     */
    private fun isAuthorizeTarget(handler: HandlerMethod): Boolean {
        // 実行されるメソッドとクラスを取得
        val method: Method = handler.method
        // @NonAuthorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(method, NonAuthorize::class.java) != null) {
            // 付与されている場合は認可せずに終了
            return false
        }
        // メソッドに対応するControllerを取得
        val controller: Class<*> = method.declaringClass
        // Controllerまたはメソッドに@Authorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(controller, Authorize::class.java) != null
                || AnnotationUtils.findAnnotation(method, Authorize::class.java) != null) {
            return true
        }

        return false
    }

    private fun getAuthUser(request: HttpServletRequest): AuthUser? {
        // Authorizationの値を取得
        val authorization = request.getHeader(AUTHORIZATION_HEADER_NAME)
        if (authorization.isNullOrBlank()) {
            return null
        }
        // Bearer tokenの形式であることをチェック
        if (!authorization.startsWith(AUTHORIZATION_PREFIX)) {
            return null
        }
        // TODO JWTトークンから認証ユーザー情報を生成
//        val token = authorization.replace(AUTHORIZATION_PREFIX, "")
        return AuthUser(UserId(1), listOf(UserRoleType.ADMIN))
    }

    private fun authorize(handler: HandlerMethod, authUser: AuthUser) {
        val method = handler.method
        val authorize = AnnotationUtils.findAnnotation(method, Authorize::class.java)
        val roles = authorize?.roles ?: return

        for (role in roles) {
            if (!authUser.userRoleTypes.contains(role)) {
                throw ResponseStatusException (HttpStatus.FORBIDDEN)
            }
        }
    }

    companion object {
        const val AUTHORIZATION_HEADER_NAME = "Authorization"
        const val AUTHORIZATION_PREFIX = "Bearer "
    }

}
