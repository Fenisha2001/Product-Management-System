package com.jsp.pms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.pms.entity.Product;
import com.jsp.pms.entity.User;
import com.jsp.pms.utility.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) ;

}
