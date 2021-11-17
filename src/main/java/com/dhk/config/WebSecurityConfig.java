package com.dhk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dhk.service.AccountDetailsService;
import com.dhk.utils.AuthEntryPointJwt;
import com.dhk.utils.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;
		
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder());
	}
	

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/api/auth/signup"
					,"/api/auth/signin"
					,"/api/v1/departments",
					"/api/v1/appointments/create",
					"/api/v1/appointments/update",
					"/api/v1/doctors/id={id}")
		.permitAll()
		.antMatchers("/api/v1/departments/**",
				"/api/v1/doctors/create",
				"/api/v1/appointments/**")
		.hasAuthority("ADMIN")
		.antMatchers("/api/v1/appointments/id={id}")
		.hasAuthority("USER")
		.anyRequest()
		.authenticated();
//		
		
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/api/login/**").permitAll()
//		.anyRequest().authenticated();
//		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
