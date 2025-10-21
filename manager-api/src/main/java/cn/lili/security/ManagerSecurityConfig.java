package cn.lili.security;

import cn.lili.cache.Cache;
import cn.lili.common.properties.IgnoredUrlsProperties;
import cn.lili.common.security.CustomAccessDeniedHandler;
import cn.lili.modules.permission.service.MenuService;
import cn.lili.modules.system.token.ManagerTokenGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * spring Security 核心配置类 Manager安全配置中心
 *
 * @author Chopper
 * @since 2020/11/14 16:20
 */
@Slf4j
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ManagerSecurityConfig {

    @Autowired
    public MenuService menuService;
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
    private CorsConfigurationSource corsConfigurationSource;
    @Autowired
    private ManagerTokenGenerate managerTokenGenerate;
    // 新增：用于获取 AuthenticationManager
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 仅对管理端接口路径生效，避免影响文档静态资源
        http.securityMatcher("/manager/**")
            // 禁止网页 iframe
            .headers(h -> h.frameOptions(f -> f.disable()))
            .authorizeHttpRequests(auth -> {
                for (String url : ignoredUrlsProperties.getUrls()) {
                    auth.requestMatchers(url).permitAll();
                }
                auth.anyRequest().authenticated();
            })
            // 允许跨域
            .cors(c -> c.configurationSource(corsConfigurationSource))
            // 关闭 CSRF（前后端分离）
            .csrf(csrf -> csrf.disable())
            // 前后端分离采用 JWT 不需要 session
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 自定义权限拒绝处理，改为返回 JSON 而非重定向
            .exceptionHandling(eh -> eh
                    .accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint((req, res, ex) ->
                            cn.lili.common.utils.ResponseUtil.output(res, 401,
                                    cn.lili.common.utils.ResponseUtil.resultMap(false, 401, "未登录或token失效"))
                    )
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        // 添加 JWT 认证过滤器（保持你原有的构造参数）
        http.addFilter(new ManagerAuthenticationFilter(
            authenticationConfiguration.getAuthenticationManager(),
            menuService,
            managerTokenGenerate,
            cache,
            ignoredUrlsProperties
        ));

        return http.build();
    }
}
