package com.cubes.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubes.main.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
