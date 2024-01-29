package org.example.firstboot.auth;

import org.example.firstboot.entity.User;
import org.example.firstboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 该类主要实现查询用户详情信息
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return new AccountUser(user.getId(), user.getName(), user.getPassword(), getUserAuthority(user.getId()));
    }

    /**
     * 获取用户权限信息（角色、菜单权限）
     */
    public List<GrantedAuthority> getUserAuthority(Long userId) {
        // 获取用户权限级别
        String authority = userService.getUserRole(userId);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
