package com.asset.foundation.user;

import com.asset.foundation.configuration.Authority;
import com.asset.foundation.utility.abstractclass.AbstractClass;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractClass<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String username;

    private String fullName;

    @Column(nullable = true)
    private String password;

    private Status status;

    private String email;

    private UserType userType;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorites = new HashSet<>();
        authorites.add(new Authority(userType.name()));
        return authorites;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    

}
