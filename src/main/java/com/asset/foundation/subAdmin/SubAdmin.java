package com.asset.foundation.subAdmin;

import com.asset.foundation.user.Status;
import com.asset.foundation.user.User;
import com.asset.foundation.utility.abstractclass.AbstractClass;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sub_admin")
public class SubAdmin extends AbstractClass<Long> {

    private String playerCode;

    private String address;
//
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    private Status status = Status.ACTIVE;

//    @ElementCollection
//    private List<String> mobileNumber;

//    private Date dob;
    
    

    @Builder.Default
    private boolean enabled = true;

	public String getPlayerCode() {
		return playerCode;
	}

	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



}
