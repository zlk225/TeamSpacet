package cn.moling.spacet.utils;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zhanglk
 * @Date: 2019/4/18 14:38
 * @Description:
 */
public class CookieUtil {

    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);// secure=true => work on HTTPS only.
        cookie.setHttpOnly(true);// invisible to JavaScript
        cookie.setMaxAge(maxAge);// maxAge=0: expire cookie now, maxAge<0: expire cookiie on browser exit.
        cookie.setDomain(domain);// visible to domain only.
        cookie.setPath("/");// visible to all paths
        httpServletResponse.addCookie(cookie);
    }

    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
}
