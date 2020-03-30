package com.jayer.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayer.vhr.model.Hr;
import com.jayer.vhr.model.ResBean;
import com.jayer.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration()
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    CustomUrlDecisionManager decisionManager;
    @Autowired
    VerificationFilter verificationFilter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login","/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verificationFilter,UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(decisionManager);
                        object.setSecurityMetadataSource(securityMetadataSource);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        Hr hr = (Hr) auth.getPrincipal();
                        hr.setPassword(null);
                        ResBean ok = ResBean.ok("登录成功",hr);
                        PrintWriter out = resp.getWriter();
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(ok));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(500);
                        ResBean resBean = ResBean.error("登录失败");
                        if(e instanceof LockedException){
                            resBean.setMsg("账户被锁定，请联系管理员");
                        }else if(e instanceof BadCredentialsException){
                            resBean.setMsg("用户名或者密码输入错误，请重新输入");
                        }else if(e instanceof DisabledException){
                            resBean.setMsg("用户被禁用，请联系管理员");
                        }else if(e instanceof AccountExpiredException){
                            resBean.setMsg("账户过期，请联系管理员");
                        }else if(e instanceof CredentialsExpiredException){
                            resBean.setMsg("密码过期，请联系管理员");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(resBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        ResBean resBean = ResBean.ok("注销成功!");
                        out.write(new ObjectMapper().writeValueAsString(resBean));
                        out.flush();
                        out.close();

                    }
                })
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        resp.setStatus(401);
                        PrintWriter out = resp.getWriter();
                        ResBean resBean = ResBean.error("访问失败");
                        resBean.setStatus(401);
                        if(e instanceof InsufficientAuthenticationException){
                            resBean.setMsg("请求失败，请联系管理员");
                        }
                        out.write(new ObjectMapper().writeValueAsString(resBean));
                    }
                });



    }
}











