package com.dhk.utils;

import java.awt.datatransfer.FlavorEvent;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dhk.entity.Account;
import com.dhk.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public AccountDetails(Long id, String username, String password,
			Collection<? extends GrantedAuthority > authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static AccountDetails build(Account account ) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map( role-> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList()); 

		return new AccountDetails(
					account.getId(),
					account.getUsername(),
					account.getPassword(),
					authorities
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stubs
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
