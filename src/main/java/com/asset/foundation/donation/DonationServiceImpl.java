package com.asset.foundation.donation;

import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Override
    public String save(RedirectAttributes redirectAttributes, DonationDto donationDto) {
        Donation donation = toEntity(donationDto);
        donationRepository.save(donation);
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
        return "redirect:/donation/paypal";
    }

    @Override
    public List<DonationDto> findAll() {
        return toDto(donationRepository.findByStatus(com.asset.foundation.user.Status.ACTIVE));
    }

    @Override
    public DonationDto findById(Long id) {
        return toDto(donationRepository.findById(id).orElse(null));
    }

    public Donation toEntity(DonationDto dto) {
        if (dto == null) {
            return null;
        }
        Donation entity;
        entity = new Donation();
        toEntity(dto, entity);
        return entity;
    }

    public Donation toEntity(DonationDto dto, Donation entity) {
        if (entity == null || dto == null) {
            return null;
        }
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setStreetAd1(dto.getStreetAd1());
        entity.setStreetAd2(dto.getStreetAd2());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPostal(dto.getPostal());
        entity.setCountry(dto.getCountry());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setComments(dto.getComments());
        if (dto.getStatus().equals(com.asset.foundation.user.Status.INACTIVE.getValue())) {
            entity.setStatus(com.asset.foundation.user.Status.INACTIVE);
        } else {
            entity.setStatus(com.asset.foundation.user.Status.ACTIVE);
        }
        return entity;
    }

    public DonationDto toDto(Donation entity) {
        if (entity == null) {
            return null;
        }
        DonationDto dto = new DonationDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setStreetAd1(entity.getStreetAd1());
        dto.setStreetAd2(entity.getStreetAd2());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setPostal(entity.getPostal());
        dto.setCountry(entity.getCountry());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setComments(entity.getComments());
        dto.setStatus(entity.getStatus().getValue());
        return dto;
    }

    public List<DonationDto> toDto(List<Donation> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
