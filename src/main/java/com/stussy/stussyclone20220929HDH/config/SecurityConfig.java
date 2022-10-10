package com.stussy.stussyclone20220929HDH.config;

import com.stussy.stussyclone20220929HDH.handler.auth.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  //기존의 WebSecurityConfigurerAdapter 클래스를 해당 SecurityConfig로 대체하겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.httpBasic().disable();
       http.authorizeRequests()  //모든 요청시에 실행을 해라

               /*<<<<<<<<<<<<<<<<<<<Page>>>>>>>>>>>>>>>>>>>>*/
               .antMatchers("/admin/**")  //지정한 주소를 가지고 hasRole("ADMIN")권한을 줄건지 정함.
               //.hasRole("ADMIN")  //등급 확인(ROLE_ADMIN)                //하나의 권한을 줄 때 사용
               .access("hasRole('ADMIN') or hasRole('MANAGER')")  //여러개의 권한을 줄 때 사용
               .antMatchers("/account")  //해당 요청 주소들은
               .access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
               //.authenticated()  //인증이 필요하다.
               .antMatchers("/", "/index", "/collections/**")
               .permitAll()
               .antMatchers( "/account/login", "/account/register")
               .permitAll()     //모든 접근 권한을 허용해라.

               /*<<<<<<<<<<<<<<<<<<< RESOURCE>>>>>>>>>>>>>>>>>>*/
               .antMatchers("/static/**", "/image/**")  //static, image로 요청 받은 것은
               .permitAll()

               /*<<<<<<<<<<<<<<<<<< API >>>>>>>>>>>>>>*/
               .antMatchers("/api/account/register")
               .permitAll()

               .anyRequest()    //antMatchers 외에 다른 모든 요청들은
               .permitAll()
               //.denyAll()       // 모든 접근을 차단해라

               .and()//그리고
               .formLogin()  //폼로그인 방식으로 인증을 해라
               .usernameParameter("email")
               .loginPage("/account/login")  //우리가 만든 로그린 페이지를 사용해라. GET 요청
               .loginProcessingUrl("/account/login")  //로그인 로직(PrincipalDetailsService) POST 요청
               .failureHandler(new AuthFailureHandler())
               .defaultSuccessUrl("/index");  //

    }
}
