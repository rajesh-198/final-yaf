package com.asset.foundation.user;

import java.util.List;

public interface UserService {

    UserDTO saveDto(UserDTO user);

    User findByUserName(String username);

    UserDTO findUserDtoById(Long id);

    UserDTO changePassword(ChangePasswordDTO changePasswordDTO);

    List<UserDTO> findAll();

    User findUserById(Long id);

    User deleteUser(User user);

}
