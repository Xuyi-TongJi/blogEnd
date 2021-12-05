package edu.seu.shiro;

import cn.hutool.core.bean.BeanUtil;
import edu.seu.entity.User;
import edu.seu.service.UserService;
import edu.seu.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 访问数据库，密码校验逻辑
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AccountRealm(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 密码校验逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String userID = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userID));
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        } else if (user.getStatus() == -1){
            throw new LockedAccountException("账户被锁定");
        }
        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(user, accountProfile);  // 公开一部分内容
        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
}
