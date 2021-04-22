package net.add1s.ofm.config.auth;

import net.add1s.ofm.config.auth.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author pj.w@qq.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final MyExpiredSessionStrategy myExpiredSessionStrategy;
    private final MyInvalidSessionStrategy myInvalidSessionStrategy;
    private final MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;
    private final MyLogoutSuccessHandler myLogoutSuccessHandler;
    private final LoginFilter loginFilter;
    private final MyPasswordEncoder myPasswordEncoder;
    private final DataSource dataSource;

    public SecurityConfig(
            MyUserDetailsService myUserDetailsService,
            MyAuthenticationSuccessHandler myAuthenticationSuccessHandler,
            MyAuthenticationFailureHandler myAuthenticationFailureHandler,
            MyExpiredSessionStrategy myExpiredSessionStrategy,
            MyInvalidSessionStrategy myInvalidSessionStrategy,
            MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler,
            MyLogoutSuccessHandler myLogoutSuccessHandler,
            LoginFilter loginFilter,
            MyPasswordEncoder myPasswordEncoder,
            DataSource dataSource) {
        this.myUserDetailsService = myUserDetailsService;
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.myExpiredSessionStrategy = myExpiredSessionStrategy;
        this.myInvalidSessionStrategy = myInvalidSessionStrategy;
        this.myAuthenticationAccessDeniedHandler = myAuthenticationAccessDeniedHandler;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.loginFilter = loginFilter;
        this.myPasswordEncoder = myPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and()
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")  // 自定义form name
//                .rememberMeCookieName("remember-me-cookie-custom")  // 自定义Cookie name
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(Long.valueOf(TimeUnit.DAYS.toSeconds(2)).intValue()) // 令牌有效期：2天
                .userDetailsService(myUserDetailsService)
                .and()
                .logout()   // 默认操作：①当前SESSION失效 ②当前用户的"remember-me"功能失效 ③清除当前的SecurityContext ④重定向到"loginPage(xx)"配置的指定页面
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/**"
//                        "/login.html",
//                        "/login",
//                        "/register.html",
//                        "/register",
//                        "/",
//                        "/imageCaptcha",
//                        "/common/**",
//                        "/chat/doChat.html"
                ).permitAll()  // 无需认证
//                .antMatchers("/", "/index").authenticated() // 登录即可访问
                .anyRequest().access("@rbacService.hasPermission(request, authentication)") // 参数名称必须是"request"和"authentication"
//                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login.html")  // SESSION超时后重新登录，使用server.servlet.session.timeout配置，值eg: 10s
                .sessionFixation().newSession() // session劫持保护，每次登录都重新生成
                .maximumSessions(1) // 一个账户只允许一个session连接
                .maxSessionsPreventsLogin(false)    // 允许再次登录，但之前的登录状态将被下线
                .expiredSessionStrategy(myExpiredSessionStrategy)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(myPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 开放静态资源
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new MyPasswordEncoder();
//    }
}
