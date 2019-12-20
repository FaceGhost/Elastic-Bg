package com.faceghost.elasticbg.shiro;

import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.shiro.ShiroUser;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.service.SystemPermissionService;
import com.faceghost.elasticbg.service.SystemRoleService;
import com.faceghost.elasticbg.service.SystemUserService;
import com.faceghost.elasticbg.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemPermissionService systemPermissionService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        //登录用户名
        String  username = (String) token.getUsername();
        SystemUser bean = systemUserService.getSystemUserByUserName(username);
        if(bean == null){
            throw new UnknownAccountException();//没有找到账户
        }else if(ValidateUtil.validateBlank(bean.getStatus()+"") || bean.getStatus() == 0){
            throw new LockedAccountException();
        }

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(bean.getId());
        shiroUser.setName(bean.getName());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                shiroUser,//用户
                bean.getPassword(),//密码
                ByteSource.Util.bytes(PasswordUtil.getCredentialsSalt(bean)),//Salt id+salt
                getName());//realm name

        clearCachedAuthenticationInfo((String)token.getPrincipal());

        return authenticationInfo;

    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SystemUser bean = systemUserService.getSystemUserByUserName(shiroUser.getName());
        if(bean != null){
            Set<String> roles = new HashSet<String>();
            Set<String> permissions = new HashSet<String>();
            List<Integer> rolesIds = new ArrayList<Integer>();
            //角色集合
            List<SystemRoleVo> listRole = systemRoleService.findRolesBySystemUserId(bean.getId());
            if(listRole != null && !listRole.isEmpty()){
                for(SystemRoleVo sr : listRole){
                    String name = sr.getName();
                    roles.add(name);
                    rolesIds.add(sr.getId());
                }
            }
            authorizationInfo.setRoles(roles);

            if(!roles.isEmpty()){
                //权限集合
                List<SystemPermissionVo> listSystemPermissions =  systemPermissionService.findPermissionBySystemRoleIds(rolesIds);
                if(listSystemPermissions != null && !listSystemPermissions.isEmpty()){
                    for(SystemPermissionVo sp : listSystemPermissions){
                        if(!ValidateUtil.validateBlank(sp.getPermission())){
                            permissions.add(sp.getPermission());
                        }
                    }
                }
                authorizationInfo.setStringPermissions(permissions);
            }
        }else{
            authorizationInfo.setRoles(null);
            authorizationInfo.setStringPermissions(null);
        }

        log.info("所属角色集合【roles】: " + authorizationInfo.getRoles());
        log.info(("所属权限集合【permission】: " + authorizationInfo.getStringPermissions()));
        return  authorizationInfo;
    }

    public  void clearCachedAuthenticationInfo(String principal){
        log.info("已清除["+principal + "]的认证缓存信息");
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthenticationInfo(principals);
    }
}
