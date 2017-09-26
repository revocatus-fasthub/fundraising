package tz.co.fasthub.fundraising.configuration;

/**
 * Created by naaminicharles on 9/21/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdministratorSecurityConfig extends WebSecurityConfigurerAdapter {



    private UserDetailsService userDetailsService;

    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Autowired
    public AdministratorSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http    .headers().cacheControl().and().defaultsDisabled()
//                .contentTypeOptions();

        http    .authorizeRequests()
                .antMatchers("/fund/main").permitAll()
//                .antMatchers("/**").hasRole("USER")
//                .antMatchers("/survey/**").authenticated()
//                .antMatchers("/survey/users**").hasRole("ADMIN")

//                .anyRequest().authenticated().and()
                .anyRequest().permitAll().and()
                .formLogin()
                .loginPage("/fund/login").permitAll().loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/fund/home")
                .failureUrl("/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/fund/login")

                .and()
                .csrf().disable();

        http
                .exceptionHandling().accessDeniedPage("/403");

//        accessDeniedHandler(accessDeniedHandler);

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/css/**");
        //  web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
    }


    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    public void setAccessDeniedHandler(CustomAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

}