package com.jsp.pms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
//@Setter
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull
	private String userName;

	@NotBlank(message = "Email is required")
	@NotNull(message = "Email is required")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String userEmail;

	@NotBlank(message = "Password is required")
	@NotNull(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String userPassword;

	@Min(value = 6000000000l, message = "Phone number is invalid, should be greater than 6000000000")
	@Max(value = 9999999999l, message = "Phone number is invalid, should be lesser than 9999999999")
	private long userMobileNum;

		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		public long getUserMobileNum() {
			return userMobileNum;
		}
		public void setUserMobileNum(long userMobileNum) {
			this.userMobileNum = userMobileNum;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
					+ userPassword + ", userMobileNum=" + userMobileNum + "]";
		}



}
