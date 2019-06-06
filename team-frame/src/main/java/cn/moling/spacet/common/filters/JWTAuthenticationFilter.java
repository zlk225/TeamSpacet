package cn.moling.spacet.common.filters;

import cn.moling.spacet.common.security.AjaxAuthenticationFailureHandler;
import cn.moling.spacet.common.security.AjaxAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @Auther: zhanglk
 * @Date: 2019/4/2 17:17
 * @Description: 用户账号的验证
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;
    private AjaxAuthenticationSuccessHandler authenticationSuccessHandler;
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AjaxAuthenticationSuccessHandler authenticationSuccessHandler, AjaxAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationManager = authenticationManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String remember = request.getParameter("remember");
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        if(remember == null){
            remember = "0";
        }

        username = username.trim();
        password = password.trim();
        rememberMe.set(Integer.parseInt(remember));
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
        );
    }
}
