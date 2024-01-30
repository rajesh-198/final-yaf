package com.asset.foundation.subAdmin;

import com.asset.foundation.user.User;
import com.asset.foundation.user.UserDTO;

import java.util.List;

public interface SubAdminService {

    SubAdminDTO saveDto(UserDTO userDto, SubAdminDTO subAdminDTO);

    // PlayerDTO findByPlayerNameAndStatus(String name, Status status);

    List<SubAdminDTO> findAllWithStatus();

    List<SubAdminDTO> findAll();

    SubAdminDTO findByUserAndStatus(User user);

    SubAdminDTO editDto(UserDTO userDTO, SubAdminDTO subAdminDTO);

    SubAdminDTO findPlayerDtoById(Long id);

    SubAdmin deletePlayer(Long playerId);

    SubAdmin findPlayerById(Long id);

}
