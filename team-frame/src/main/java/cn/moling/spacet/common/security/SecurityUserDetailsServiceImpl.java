package cn.moling.spacet.common.security;

import cn.moling.spacet.auth.domain.SpaceUser;
import cn.moling.spacet.auth.service.SpaceUserService;
import cn.moling.spacet.common.entity.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: zhanglk
 * @Date: 2019/5/30 13:41
 * @Description:    用户认证、权限
 */
@Component
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SpaceUserService spaceUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过username查询用户

        SpaceUser spaceUser = spaceUserService.getSpaceUserByUserName(username);
        if(spaceUser == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }
        SecurityUserDetails user = new SecurityUserDetails(spaceUser);
        Set authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户角色
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);

        return user;
    }
}
