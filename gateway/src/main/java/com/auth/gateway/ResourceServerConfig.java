package com.auth.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* auth 서버 인증 설정 */
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/refresh/**").permitAll()
                /* auth 서버 인증 설정 끝*/
                /* 회원 서버 인증 설정 */
                .antMatchers("/api/signup/**").permitAll()
                /* 회원 서버 인증 설정 끝*/
                /* 기타 설정 */
                .antMatchers("/api/**").authenticated();
                /* 기타 설정 끝*/
    }
}