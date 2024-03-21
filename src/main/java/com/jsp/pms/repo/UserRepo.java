package com.jsp.pms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.pms.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
