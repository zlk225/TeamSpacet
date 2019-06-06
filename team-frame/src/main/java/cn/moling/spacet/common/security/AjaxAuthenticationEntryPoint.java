package cn.moling.spacet.common.security;

import cn.moling.spacet.common.enums.ResultEnum;
import cn.moling.spacet.utils.CommandResult;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zhanglk
 * @Date: 2019/5/30 11:03
 * @Description:    用户未登录时返回给前端的数据
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.getWriter().write(JSON.toJSONString(new CommandResult(ResultEnum.USER_NEED_AUTHORITIES)));
    }
}
