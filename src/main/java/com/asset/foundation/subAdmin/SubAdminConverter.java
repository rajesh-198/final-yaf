package com.asset.foundation.subAdmin;

import com.asset.foundation.utility.EntityMapperBase;
import com.asset.foundation.user.Status;
import com.asset.foundation.user.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubAdminConverter implements EntityMapperBase<SubAdminDTO, SubAdmin> {

    @Autowired
    SubAdminRepository subAdminRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public SubAdmin toEntity(SubAdminDTO dto) {
        if (dto == null) {
            return null;
        }
        SubAdmin entity;
        if (dto.getId() != null) {
            entity = subAdminRepository.findPlayerById(dto.getId());
        } else {
            entity = new SubAdmin();
            toEntity(dto, entity);
            entity.setStatus(Status.ACTIVE);
        }
        return entity;
    }

    public SubAdmin convertToEntity(SubAdminDTO dto) {
        if (dto == null) {
            return null;
        }
        SubAdmin entity = new SubAdmin();
            toEntity(dto, entity);
            entity.setStatus(Status.ACTIVE);
        return entity;
    }

    @Override
    public SubAdmin toEntity(SubAdminDTO dto, SubAdmin entity) {
        if (entity == null || dto == null) {
            return null;
        }
        // entity.setAgentName(dto.getAgentName());
//        entity.setPlayerCode(dto.getPlayerCode());
//        entity.setAddress(dto.getAddress());
//        entity.setMobileNumber(dto.getMobileNumber());
//        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        Date dob = null;
//        try {
//            dob = formatter.parse(dto.getDob());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        entity.setDob(dob);
        entity.setUser(userRepository.findUserById(dto.getUserId()));
        return entity;
    }

    @Override
    public SubAdminDTO toDto(SubAdmin entity) {
        if (entity == null) {
            return null;
        }
        SubAdminDTO dto = new SubAdminDTO();
        dto.setId(entity.getId());
//        dto.setPlayerName(entity.getUser().getFullName());
//        dto.setPlayerCode(entity.getPlayerCode());
//        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus().getValue());
        dto.setUserId(entity.getUser().getId());
        dto.setUserLogin(entity.getUser().getUsername());
//        dto.setMobileNumber(entity.getMobileNumber());
//        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        String dob = formatter.format(entity.getDob());
//        dto.setDob(dob);
        return dto;
    }

    @Override
    public List<SubAdmin> toEntity(List<SubAdminDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return Collections.emptyList();
        }
        return dtoList.parallelStream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<SubAdminDTO> toDto(List<SubAdmin> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
