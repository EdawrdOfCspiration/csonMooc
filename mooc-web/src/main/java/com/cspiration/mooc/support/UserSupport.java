package com.cspiration.mooc.support;

import com.cspiration.mooc.exception.ConditionException;
import com.cspiration.mooc.utils.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，禁止转发和分享
 */
@Component
public class UserSupport {

    public Long getCurrentUserId() throws NoSuchAlgorithmException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        Long userId = TokenUtils.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户");
        }
        return userId;

    }
}
