package com.geekhome.shiro.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Shiro 配置
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * 后台身份认证realm;
	 * 
	 * @return
	 */
	@Bean(name = "adminShiroRealm")
	public AdminShiroRealm adminShiroRealm() {
		System.out.println("ShiroConfiguration.adminShiroRealm()");
		AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
		adminShiroRealm.setCredentialsMatcher(adminHashedCredentialsMatcher());
		return adminShiroRealm;
	}

	/**
	 * shiro缓存管理器; 需要注入对应的其它的实体类中： 1、安全管理器：securityManager
	 * 可见securityManager是整个shiro的核心；
	 * 
	 * @return
	 */
	@Bean(name = "ehCacheManager")
	public EhCacheManager ehCacheManager() {
		System.out.println("ShiroConfiguration.ehCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return cacheManager;
	}

	/**
	 * Shiro默认提供了三种 AuthenticationStrategy 实现： AtLeastOneSuccessfulStrategy
	 * ：其中一个通过则成功。 FirstSuccessfulStrategy ：其中一个通过则成功，但只返回第一个通过的Realm提供的验证信息。
	 * AllSuccessfulStrategy ：凡是配置到应用中的Realm都必须全部通过。 authenticationStrategy
	 * 
	 * @return
	 */
	@Bean(name = "authenticationStrategy")
	public AuthenticationStrategy authenticationStrategy() {
		System.out.println("ShiroConfiguration.authenticationStrategy()");
		return new FirstSuccessfulStrategy();
	}

	/**
	 * @see DefaultWebSessionManager
	 * @return
	 */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager() {
		System.out.println("ShiroConfiguration.defaultWebSessionManager()");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(ehCacheManager());
		sessionManager.setSessionValidationInterval(3600000 * 12);
		sessionManager.setGlobalSessionTimeout(3600000 * 12);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		cookie.setName("WEBID");
		cookie.setHttpOnly(true);
		sessionManager.setSessionIdCookie(cookie);
		return sessionManager;
	}

	/**
	 * @see org.apache.shiro.mgt.SecurityManager
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManage() {
        System.out.println("ShiroConfiguration.getDefaultWebSecurityManage()");
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();

        Map<String, Object> shiroAuthenticatorRealms = new HashMap<>();
        shiroAuthenticatorRealms.put("adminShiroRealm", adminShiroRealm());

        Collection<Realm> shiroAuthorizerRealms = new ArrayList<Realm>();
        shiroAuthorizerRealms.add(adminShiroRealm());

        CustomModularRealmAuthenticator customModularRealmAuthenticator = new CustomModularRealmAuthenticator();
        customModularRealmAuthenticator.setDefinedRealms(shiroAuthenticatorRealms);
        customModularRealmAuthenticator.setAuthenticationStrategy(authenticationStrategy());
        securityManager.setAuthenticator(customModularRealmAuthenticator);
        ModularRealmAuthorizer customModularRealmAuthorizer = new ModularRealmAuthorizer();
        customModularRealmAuthorizer.setRealms(shiroAuthorizerRealms);
        securityManager.setAuthorizer(customModularRealmAuthorizer);
        //注入缓存管理器;
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "authorizationAttributeSourceAdvisor")
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		System.out.println("ShiroConfiguration.authorizationAttributeSourceAdvisor()");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 */
	@Bean(name = "shirFilter")
	public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 增加自定义过滤
		Map<String, Filter> filters = new HashMap<>();
		filters.put("admin", new AdminFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		/**
		 * anon（匿名） org.apache.shiro.web.filter.authc.AnonymousFilter authc（身份验证）
		 * org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 * authcBasic（http基本验证）
		 * org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter logout（退出）
		 * org.apache.shiro.web.filter.authc.LogoutFilter noSessionCreation（不创建session）
		 * org.apache.shiro.web.filter.session.NoSessionCreationFilter perms(许可验证)
		 * org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter port（端口验证）
		 * org.apache.shiro.web.filter.authz.PortFilter rest (rest方面)
		 * org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter roles（权限验证）
		 * org.apache.shiro.web.filter.authz.RolesAuthorizationFilter ssl （ssl方面）
		 * org.apache.shiro.web.filter.authz.SslFilter member （用户方面）
		 * org.apache.shiro.web.filter.authc.UserFilter user
		 * 表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
		 */

		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**/login", "anon");
		filterChainDefinitionMap.put("/**/logout", "anon");
		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/admin/**", "admin");
		// 未授权界面;
		// shiroFilterFactoryBean.setUnauthorizedUrl("/console/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
	 * 
	 * @return
	 */
	@Bean(name = "adminHashedCredentialsMatcher")
	public HashedCredentialsMatcher adminHashedCredentialsMatcher() {
		System.out.println("ShiroConfiguration.adminHashedCredentialsMatcher()");
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，当于 m比如散列两次，相d5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * 注入LifecycleBeanPostProcessor
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		System.out.println("ShiroConfiguration.lifecycleBeanPostProcessor()");
		return new LifecycleBeanPostProcessor();
	}

	@ConditionalOnMissingBean
	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		System.out.println("ShiroConfiguration.getDefaultAdvisorAutoProxyCreator()");
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
}
