package com.practiceapp.userservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserProfile {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator")
	private String userId;
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private String contact;
	
	private LocalDateTime createdAt;
	
	
	
	
	/*
	 * public UserProfile(String userId, String emailId, String password, String
	 * firstName, String lastName, String contact, LocalDateTime createdAt) {
	 * super(); this.userId = userId; this.emailId = emailId; this.password =
	 * password; this.firstName = firstName; this.lastName = lastName; this.contact
	 * = contact;
	 * 
	 * this.createdAt = createdAt; }
	 */
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", emailId=" + emailId + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", contact=" + contact + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
}
