package com.asset.foundation.donation;

import com.asset.foundation.user.Status;
import com.asset.foundation.utility.abstractclass.AbstractClass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "donation")
public class Donation extends AbstractClass<Long> {
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



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	@Builder.Default
    private Status status = Status.INACTIVE;
    
    
    
    
}
