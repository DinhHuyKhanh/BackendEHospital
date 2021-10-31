package com.dhk.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByusername(String username);
	Boolean  existsByUsername(String username);
}
