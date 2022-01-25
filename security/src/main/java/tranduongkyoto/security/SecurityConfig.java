package tranduongkyoto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/design", "/order/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/ingredients").permitAll()
                .antMatchers("/**").access("permitALl");
//                .and()
//                    .formLogin()
//                        .loginPage("/login")
//                .and()
//                    .httpBasic()
//                        .realmName("Taco Cloud")
//                .and()
//                    .logout()
//                        .logoutSuccessUrl("/")
//                .and()
//                    .csrf()
//                        .ignoringAntMatchers("/h2-console/**", "/ingredients/**", "/design", "/order/**")
//                .and()
//                    .headers()
//                    .frameOptions()
//                        .sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
