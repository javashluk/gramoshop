package tfzr.store.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http
        .authorizeRequests()
        .antMatchers("/registration/**").permitAll()
       .antMatchers("/**/bootstrap.min.css").permitAll()
       .antMatchers("/**/jquery.min.js").permitAll()
		.antMatchers("/**/bootstrap.min.js").permitAll()
		.antMatchers("/**/jquery.js").permitAll()
		.antMatchers("/**/login.css").permitAll()
		.antMatchers("/**/glyphicons-halflings-regular.woff2").permitAll()
        	.antMatchers("/public/**").hasAnyAuthority("USER")
        	.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
        	.antMatchers("/login").hasAnyAuthority("NULL")
            .anyRequest().authenticated()	
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/", true)
            .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("remember-me")
            .logoutSuccessUrl("/login")
            .permitAll()
            .and()
            .rememberMe();
           
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
