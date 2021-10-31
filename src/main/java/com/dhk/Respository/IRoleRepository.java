package com.dhk.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.entity.ERole;
import com.dhk.entity.Role;

@Repository
public interface IRoleRepository  extends JpaRepository<Role,Integer>{
	
	Optional<Role> findByName(ERole roleName);
}
