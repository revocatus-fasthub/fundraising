package tz.co.fasthub.fundraising.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;
//
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
    private UserDetailsService userDetailsService;

    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
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
		
		http.
			authorizeRequests()
                .antMatchers("/landingPage").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
//				.antMatchers("/login").permitAll()
//				.antMatchers("/registration").permitAll()
//				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//				.authenticated()
				.and()
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
//                .accessDeniedPage("/access-denied");

	}

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