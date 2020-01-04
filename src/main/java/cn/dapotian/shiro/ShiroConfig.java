package cn.dapotian.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ch.qos.logback.core.net.LoginAuthenticator;

/**
 * shiro配置类 Shiro核心API
 * 		1.Subject:用户主体（把操作交给SecurityManager）
 * 		2.SecurityManager：安全管理器（关联Realm）
 * 		3.Realm：Shiro连接数据桥梁
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfig {
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截器
		 * 	常用的过滤器:
		 * 		anon:无需认证(登录)也可以访问
		 * 		authc:必须认证(登录)才可以访问
		 * 		user:开启RememberMe功能
		 * 		perms:该资源必须授予资源权限才可以访问
		 * 		role:该资源必须得到角色权限才可以访问
		 */
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		/**
		 * filterChainDefinitionMap.put("/user/add","authc");
		 * 	key:需要拦截的请求地址
		 * 	value:拦截方式
		 */
		/*
		 * filterChainDefinitionMap.put("/user/add","authc");
		 * filterChainDefinitionMap.put("/user/update","authc");
		 */
		
		
		/**
		 * 拦截所有请求
		 */
		filterChainDefinitionMap.put("/user/index","anon");
		filterChainDefinitionMap.put("/user/login","anon");
		filterChainDefinitionMap.put("/user/*","authc");
		
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap );
		
		
		/**
		 * 拦截后的自定义跳转
		 */
		
		shiroFilterFactoryBean.setLoginUrl("/user/loginPage");
	
		
		return shiroFilterFactoryBean;		
	}
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联Realm
		securityManager.setRealm(userRealm);
		return securityManager;

		
	}
	/**
	 * 创建Realm
	 * 	把方法返回的对象注入到Spring容器中
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}

}
