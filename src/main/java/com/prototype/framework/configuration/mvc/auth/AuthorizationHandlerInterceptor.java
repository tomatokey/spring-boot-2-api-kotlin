package com.prototype.framework.configuration.mvc.auth;

import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.architecture.layer_03_domain.auth.AuthUser;
import com.prototype.framework.utils.ObjectUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

    private final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final String AUTHORIZATION_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod hm && isAuthorizeTarget(hm)) {
            final Optional<AuthUser> authUser = getAuthUser(request);
            if (authUser.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            // 認証
            authorize(hm, authUser.get());
            // 認証ユーザー情報をContextに設定
            SecurityContextHolder.getContext().setAuthentication(authUser.get());
        }

        return true;
    }

    /**
     * 認証対象かどうか判定します
     *
     * @param handler
     * @return
     */
    private boolean isAuthorizeTarget(HandlerMethod handler) {
        // 実行されるメソッドとクラスを取得
        final Method method = handler.getMethod();
        // @NonAuthorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(method, NonAuthorize.class) != null) {
            // 付与されている場合は認可せずに終了
            return false;
        }
        // メソッドに対応するControllerを取得
        final Class<?> controller = method.getDeclaringClass();
        // Controllerまたはメソッドに@Authorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(controller, Authorize.class) != null
                || AnnotationUtils.findAnnotation(method, Authorize.class) != null) {
            return true;
        }

        return false;
    }

    private Optional<AuthUser> getAuthUser(HttpServletRequest request) {
        // Authorizationの値を取得
        final String authorization = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (ObjectUtils.isEmpty(authorization)) {
            return Optional.empty();
        }
        // Bearer tokenの形式であることをチェック
        if (!authorization.startsWith(AUTHORIZATION_PREFIX)) {
            return Optional.empty();
        }
        // TODO JWTトークンから認証ユーザー情報を生成
        final String token = authorization.replace(AUTHORIZATION_PREFIX, "");
        return Optional.of(new AuthUser(new UserId(1), List.of(UserRoleType.ADMIN)));
    }

    private void authorize(HandlerMethod handler, AuthUser authUser) {
        final Method method = handler.getMethod();
        final Authorize authorize = AnnotationUtils.findAnnotation(method, Authorize.class);
        final UserRoleType[] roles = authorize.roles();
        if (ObjectUtils.isEmpty(roles)) {
            return;
        }

        for (UserRoleType role: roles) {
            if (!authUser.userRoleTypes().contains(role)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
    }

}
