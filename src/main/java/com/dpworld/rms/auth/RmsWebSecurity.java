//package com.dpworld.rms;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/login**","/callback/", "/webjars/**", "/error**","/h2-console/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//        /* Remove this code for Prod */
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//    }
//}