package com.asset.foundation.subAdmin;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubAdminError {

    private String playerName;
    private String address;
    private String playerCode;
    private List<String> mobileNumber;
    private String dob;
    private Boolean valid;
    private String size;
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPlayerCode() {
		return playerCode;
	}
	public void setPlayerCode(String playerCode) {
		this.playerCode = playerCode;
	}
	public List<String> getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(List<String> mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
    

}
