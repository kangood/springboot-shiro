package cn.dapotian.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dapotian.domain.User;
import cn.dapotian.service.UserService;

/**
 * 自定义Realm
 * 
 * @author Administrator
 *
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("执行授权逻辑");
		return null;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行认证逻辑");
//		String username = "root";
//		String password = "admin";
		// TODO Auto-generated method stub
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
//		if (!usernamePasswordToken.getUsername().equals(username)) {
		User user = userService.findUserByUsername(usernamePasswordToken.getUsername());
		if (user == null) {
			// 如果用户名不存在，直接返回null，Shiro底层会抛出UnknownAccountException
			return null;
		}

		return new SimpleAuthenticationInfo("", user.getPassword(), "");
	}

}
