package com.basic.demo.config;

import com.basic.demo.repository.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountRepositoryImpl myUserDetailService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*Cài đặt cách lấy thông tin UserDetail, cơ chế Encoder...*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login").permitAll()
                /*Login thanh cong se chuyen huong ve URL man hinh hien tai,neu truy cap truc tiep /login thi login thanh cong se chuyen huong ve /student */
                .defaultSuccessUrl("/").permitAll().
                and().authorizeRequests().antMatchers("/**","/user/create").permitAll().
                and().authorizeRequests().antMatchers("/entry**","/comments/**").hasRole("USER")
                .anyRequest().authenticated();
            /* Tất cả request gởi lên server không cần thực hiện xác thực*/
//                    .authorizeRequests().anyRequest().permitAll();

        http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 2);

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memoryTokenRepository = new InMemoryTokenRepositoryImpl();
        return memoryTokenRepository;
    }
}
