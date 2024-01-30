package com.asset.foundation.donation;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String streetAd1;
    private String streetAd2;
    private String city;
    private String state;
    private String postal;
    private String country;
    private String phoneNumber;
    private String comments;
    

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStreetAd1() {
		return streetAd1;
	}


	public void setStreetAd1(String streetAd1) {
		this.streetAd1 = streetAd1;
	}


	public String getStreetAd2() {
		return streetAd2;
	}


	public void setStreetAd2(String streetAd2) {
		this.streetAd2 = streetAd2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPostal() {
		return postal;
	}


	public void setPostal(String postal) {
		this.postal = postal;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	@Builder.Default
    private String Status = com.asset.foundation.user.Status.INACTIVE.getValue();
    
    
}
