package com.vsredshift.SpringSecurity.security;

import com.vsredshift.SpringSecurity.jwt.JwtConfig;
import com.vsredshift.SpringSecurity.jwt.JwtTokenVerifier;
import com.vsredshift.SpringSecurity.jwt.JwtUsernameAndPasswordFilter;
import com.vsredshift.SpringSecurity.services.AppMemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.vsredshift.SpringSecurity.security.UserRole.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppMemberService appMemberService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(CUSTOMER.name())
                // Before we added @PreAuthorize to methods
//                .antMatchers(DELETE, "/admin/**").hasAuthority(SUBSCRIPTION_WRITE.getPermission())
//                .antMatchers(POST, "/admin/**").hasAuthority(SUBSCRIPTION_WRITE.getPermission())
//                .antMatchers(PUT, "/admin/**").hasAuthority(SUBSCRIPTION_WRITE.getPermission())
//                .antMatchers(GET,"/admin/**").hasAnyRole(ADMIN.name(), JUNIOR_ADMIN.name())
                .anyRequest()
                .authenticated()
                // Using Basic Auth
//                .and()
//                .httpBasic();
                // Using form based auth
                /*
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/subscriptions", true)
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) DAYS.toSeconds(21))   // defaults to 2 weeks
                .key("somethingVerySecurity!")
                .rememberMeParameter("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("logout", "GET")) // logout with GET if csrf enabled
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login")*/
                ;


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appMemberService);
        return provider;
    }

}


/*
    // Manual Injection of users before DAO config

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmith = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
//                .roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails adminJunior = User.builder()
                .username("junior")
                .password(passwordEncoder.encode("junior"))
//                .roles(JUNIOR_ADMIN.name())
                .authorities(JUNIOR_ADMIN.getGrantedAuthorities())
                .build();

        UserDetails guest = User.builder()
                .username("guest")
                .password(passwordEncoder.encode("guest"))
                .authorities(GUEST.getGrantedAuthorities())
//                .roles(GUEST.name())
                .build();

        return new InMemoryUserDetailsManager(
                annaSmith,
                admin,
                adminJunior,
                guest
        );

    }
*/

