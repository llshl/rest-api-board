package com.example.restapiboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
/*
        http.authorizeRequests()
                .antMatchers("/images/**").permitAll()          //image 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()             //css 폴더를 login 없이 허용
                .antMatchers("/user/**").permitAll()            //user로 시작하는 모든 부분들은 인증없이 접근가능
                .antMatchers("/h2-console/**").permitAll()      //db도 인증없이 접근가능
                .anyRequest().authenticated()

                .and()
                .formLogin()                            //로그인페이지는 인증 필요 없음
                .loginPage("/user/login")               //로그인 페이지 지정
                .failureUrl("/user/login/error")        //로그인 실패 시 어디로 갈지 지정
                .defaultSuccessUrl("/")                 //로그인 완료 시 이동할 페이지 지정
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/user/logout")              //로그아웃 컨트롤러를 따로 만들 필요 없이 이 요청이 들어오면 로그아웃
                .permitAll()

                .and()
                .exceptionHandling()                    //인가가 안된 사용자일 경우
                .accessDeniedPage("/user/forbidden");   //포비든 페이지로 이동*/
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
