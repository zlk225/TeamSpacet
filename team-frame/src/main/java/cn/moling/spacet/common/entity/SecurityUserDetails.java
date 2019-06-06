package cn.moling.spacet.common.entity;

import cn.moling.spacet.auth.domain.SpaceUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @Auther: zhanglk
 * @Date: 2019/5/30 13:41
 * @Description:
 */
@Component
public class SecurityUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    public SecurityUserDetails() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public SecurityUserDetails(SpaceUser user) {
        id = user.getUserId();
        username = user.getUserName();
        password = user.getPassword();
        // 这里只存储了一个角色的名字
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() { // 最重点Ⅰ
        return this.password;
    }

    @Override
    public String getUsername() { // 最重点Ⅱ
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账号凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
