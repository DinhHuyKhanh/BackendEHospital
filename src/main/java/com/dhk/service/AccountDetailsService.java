package com.dhk.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhk.DTO.JwtResponse;
import com.dhk.DTO.LoginRequest;
import com.dhk.DTO.MessageResponse;
import com.dhk.DTO.SignupRequest;
import com.dhk.Respository.IAccountRepository;
import com.dhk.Respository.IRoleRepository;
import com.dhk.entity.Account;
import com.dhk.entity.ERole;
import com.dhk.entity.Role;
import com.dhk.utils.AccountDetails;
import com.dhk.utils.JwtUtils;

@Service
public class AccountDetailsService implements UserDetailsService,IAccountDetailsService{

	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account =  accountRepository.findByusername(username)
						.orElseThrow( () -> new UsernameNotFoundException("User Not Found with username" + username) );
						
		return AccountDetails.build(account);
	}
	
	@Override
	public JwtResponse authenticateAccount(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
		
		List<String> roles = accountDetails.getAuthorities().stream()
				.map(item->item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, 
								accountDetails.getId(),
								accountDetails.getUsername(),
								roles);
	}
	

	@Override
	public MessageResponse registerAccount(SignupRequest signupRequest) {
		// TODO Auto-generated method stub
		if(accountRepository.existsByUsername(signupRequest.getUsername())) {
			return new MessageResponse(" Error: Username is already taken !");
		}
		
		Account account = new Account();
		account.setUsername(signupRequest.getUsername());
		account.setPassword(encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoleSet = signupRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		if(strRoleSet == null  || strRoleSet.size() == 0) {
			Role userRole = roleRepository.findByName(ERole.USER).
					orElseThrow( ()-> new RuntimeException("Error: role is not found.") );
			roles.add(userRole);
		}else {
			strRoleSet.forEach(role->{
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ADMIN)
									.orElseThrow( ()->new RuntimeException("Error: role is not found.") );
					roles.add(adminRole);
					break;					
				default:
					Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow( ()->new RuntimeException("Error: role is not found.") );
					roles.add(userRole);
					break;
				}
			});
		}
		
		account.setRoles(roles);
		accountRepository.save(account);
		return new MessageResponse("Account Register successfully!");
	}
	
	
	
}
