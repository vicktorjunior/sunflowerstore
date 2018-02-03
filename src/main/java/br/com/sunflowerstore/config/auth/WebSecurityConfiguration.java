package br.com.sunflowerstore.config.auth;

import br.com.sunflowerstore.service.UserDetailsImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by rodrigo on 2/22/17.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsImplService accountUserDetailsService;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/photos/**","/img/**", "/resources/**", "/public/**", "/dist/**", "/db/**", "/layout/**","/stylesheets/**","/javascripts/**",
				"/test/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login**", "/dist/**", "/webjars**", "/db/**", "/logout").permitAll()
				.anyRequest().authenticated().and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/login?logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
				/*//.logout().logoutSuccessUrl("/").permitAll().and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().formLogin()
				.loginPage("/login").permitAll().and()
				.logout().logoutUrl("/logout").deleteCookies("remember-me")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/login?logout").permitAll().and().rememberMe();*/
		//http.csrf().disable();

	}

}