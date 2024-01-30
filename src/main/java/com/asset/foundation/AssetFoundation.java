package com.asset.foundation;

import com.asset.foundation.user.Status;
import com.asset.foundation.user.User;
import com.asset.foundation.user.UserDTO;
import com.asset.foundation.user.UserService;
import com.asset.foundation.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssetFoundation {


    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AssetFoundation.class, args);
    }


    @Bean
    CommandLineRunner init() {

        return args -> {

            createUser("admin", UserType.SYSTEM_ADMIN, "Test@123", Status.ACTIVE);
//            createAnswer();

        };
    }

    void createUser(String username, UserType userType, String password, Status status) {

        User user = userService.findByUserName(username);

        if (user == null) {
            UserDTO newUser = new UserDTO();
            newUser.setUsername(username);
            newUser.setEmail("raj.sttp@gmail.com");
            newUser.setUserType(userType);
            newUser.setPassword(password);
            newUser.setStatus(status);
            userService.saveDto(newUser);
        }

    }

}
