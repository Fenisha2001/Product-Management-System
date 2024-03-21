package com.jsp.pms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pms.entity.User;
import com.jsp.pms.repo.UserRepo;
import com.jsp.pms.service.UserService;
import com.jsp.pms.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepo userRepo;
	private ResponseStructure<User> structure;

	public UserServiceImpl(UserRepo userRepo, ResponseStructure<User> structure) {
		super();
		this.userRepo = userRepo;
		this.structure = structure;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User uniqueUser = userRepo.save(user);
		return ResponseEntity.ok(structure.setStatuscode(HttpStatus.OK.value())
				.setMessage("User details saved successfully!!")
				.setData(uniqueUser));
	}

}
