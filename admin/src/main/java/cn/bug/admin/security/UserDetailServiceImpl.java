package cn.bug.admin.security;

import cn.bug.generator.security_jwt_vue.domain.SysRole;
import cn.bug.generator.security_jwt_vue.domain.SysUser;
import cn.bug.generator.security_jwt_vue.service.SysMenuService;
import cn.bug.generator.security_jwt_vue.service.SysRoleService;
import cn.bug.generator.security_jwt_vue.service.SysUserRoleService;
import cn.bug.generator.security_jwt_vue.service.SysUserService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 15:19
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysMenuService sysMenuService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        return new AccountUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), getUserAuthority(sysUser.getId()));

    }

    private Collection<? extends GrantedAuthority> getUserAuthority(Long id) {
        String authority = "";
        List<SysRole> roles = sysRoleService.list(
                Wrappers.lambdaQuery(SysRole.class)
                        .inSql(SysRole::getId, "select role_id from sys_user_role where user_id = " + id)
        );
        if (!CollectionUtil.isEmpty(roles)) {
            String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
            authority = roleCodes.concat(",");
        }
        System.out.println("authority = " + authority);
        return null;
    }

}
