package cn.lili.security;

import cn.lili.cache.Cache;
import cn.lili.common.properties.IgnoredUrlsProperties;
import cn.lili.common.security.CustomAccessDeniedHandler;
import cn.lili.common.utils.SpringContextUtil;
import cn.lili.modules.member.service.ClerkService;
import cn.lili.modules.member.service.StoreMenuRoleService;
import cn.lili.modules.member.token.StoreTokenGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * spring Security 核心配置类 Store安全配置中心
 *
 * @author Chopper
 * @since 2020/11/14 16:20
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class StoreSecurityConfig {

    /**
     * 忽略验权配置
     */
    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    /**
     * spring security -》 权限不足处理
     */
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private Cache<String> cache;

    @Autowired
    private StoreTokenGenerate storeTokenGenerate;

    @Autowired
    private StoreMenuRoleService storeMenuRoleService;

    @Autowired
    private ClerkService clerkService;

    /**
     * 配置安全过滤器链
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置不需要授权的URL
        String[] ignoredUrls = ignoredUrlsProperties.getUrls().toArray(new String[0]);
        
        http
            // 配置授权规则
            .authorizeHttpRequests(authz -> authz
                // 配置的url不需要授权
                .requestMatchers(ignoredUrls).permitAll()
                // 任何其他请求都需要身份认证
                .anyRequest().authenticated()
            )
            // 禁止网页iframe
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            // 配置登出
            .logout(logout -> logout.permitAll())
            // 允许跨域
            .cors(cors -> cors.configurationSource((CorsConfigurationSource) SpringContextUtil.getBean("corsConfigurationSource")))
            // 关闭跨站请求防护
            .csrf(csrf -> csrf.disable())
            // 前后端分离采用JWT，不需要session
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 自定义权限拒绝处理类
            .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
            // 添加JWT认证过滤器
            .addFilter(new StoreAuthenticationFilter(authenticationManager(authenticationConfiguration()), storeTokenGenerate, storeMenuRoleService, clerkService, cache));

        return http.build();
    }

    /**
     * 获取AuthenticationManager Bean
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 注入AuthenticationConfiguration
     */
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * 获取AuthenticationConfiguration Bean
     */
    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return this.authenticationConfiguration;
    }
}
