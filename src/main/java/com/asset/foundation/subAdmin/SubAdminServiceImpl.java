package com.asset.foundation.subAdmin;

import com.asset.foundation.user.*;
import com.asset.foundation.utility.HashPassword;
import com.asset.foundation.utility.exception.CustomBackendExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubAdminServiceImpl implements SubAdminService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    HashPassword hashPassword;
    @Autowired
    UserService userService;
    @Autowired
    private SubAdminRepository subAdminRepository;
    @Autowired
    private SubAdminConverter subAdminConverter;
    @Autowired
    private UserConverter userConverter;

    @Override
    public SubAdminDTO saveDto(UserDTO userDto, SubAdminDTO subAdminDTO) {
        userDto.setUserType(UserType.ADMIN);
        userDto.setStatus(Status.ACTIVE);
        User user = userConverter.toEntity(userService.saveDto(userDto));
        SubAdmin subAdmin = subAdminConverter.toEntity(subAdminDTO);
        subAdmin.setUser(user);
        subAdmin = subAdminRepository.save(subAdmin);
        return subAdminConverter.toDto(subAdmin);
    }

    // @Override
    // public PlayerDTO findByPlayerNameAndStatus(String name, Status status) {
    // return playerConverter.toDto(playerRepository.findByPlayerNameAndStatus(name, status));
    // }

    @Override
    public List<SubAdminDTO> findAllWithStatus() {
        return subAdminConverter.toDto(subAdminRepository.findByStatus(Status.ACTIVE));
    }

    @Override
    public List<SubAdminDTO> findAll() {
        return subAdminConverter.toDto(subAdminRepository.findAll());
    }

    @Override
    public SubAdminDTO findByUserAndStatus(User user) {
        return subAdminConverter.toDto(subAdminRepository.findByUserAndStatus(user, Status.ACTIVE));
    }

    @Override
    public SubAdminDTO editDto(UserDTO userDTO, SubAdminDTO subAdminDTO) {
        SubAdmin subAdmin = subAdminConverter.toEntity(subAdminDTO);
//        player.setAddress(playerDTO.getAddress());
//        player.setPlayerCode(playerDTO.getPlayerCode());
//        player.setMobileNumber(playerDTO.getMobileNumber());
//        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        Date dob = null;
//        try {
//            dob = formatter.parse(playerDTO.getDob());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        player.setDob(dob);
        User user = (subAdmin.getUser());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(hashPassword.hashPassword(userDTO.getPassword()));
        }
        userRepository.save(user);
        return subAdminConverter.toDto(subAdminRepository.save(subAdmin));
    }

    @Override
    public SubAdminDTO findPlayerDtoById(Long id) {
        return subAdminConverter.toDto(subAdminRepository.findPlayerById(id));
    }

    @Override
    public SubAdmin deletePlayer(Long playerId) {
        SubAdmin subAdmin = subAdminRepository.findPlayerById(playerId);
        if (subAdmin != null) {
            if (subAdmin.getStatus().equals(Status.ACTIVE)) {
                subAdmin.setStatus(Status.INACTIVE);
            } else {
                subAdmin.setStatus(Status.ACTIVE);
            }
            return subAdminRepository.save(subAdmin);
        } else {
            return null;
        }
    }

    @Override
    public SubAdmin findPlayerById(Long id) {
        SubAdmin subAdmin = subAdminRepository.findPlayerById(id);
        if (subAdmin == null) {
            throw new CustomBackendExceptions("No player found");
        }
        return subAdmin;
    }


}
