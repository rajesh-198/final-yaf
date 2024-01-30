package com.asset.foundation.user;

import com.asset.foundation.utility.HashPassword;
import com.asset.foundation.configuration.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPassword hashPassword;

    @Autowired
    UserConverter userConverter;

    @Autowired
    SecurityUtility securityUtility;

    @Override
    public UserDTO saveDto(UserDTO userDto) {
        String newPassword = hashPassword.hashPassword(userDto.getPassword());
        userDto.setPassword(newPassword);
        User user = userConverter.convertToEntity(userDto);
        user = userRepository.save(user);
        return userConverter.toDto(user);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsernameAndStatus(username,Status.ACTIVE);
    }

    @Override
    public UserDTO findUserDtoById(Long id) {
        return userConverter.toDto(userRepository.findUserById(id));
    }

    @Override
    public UserDTO changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = securityUtility.getSecurity();
        user.setPassword(hashPassword.hashPassword(changePasswordDTO.getNewPassword()));
        return userConverter.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAll() {
        return userConverter.toDto(userRepository.findByStatus(Status.ACTIVE));
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User deleteUser(User user) {
        user = userRepository.findUserById(user.getId());
        if (user != null) {
            if (user.getStatus().equals(Status.ACTIVE)) {
                user.setStatus(Status.INACTIVE);
            } else {
                user.setStatus(Status.ACTIVE);
            }
            return userRepository.save(user);
        } else {
            return null;
        }
    }

}
