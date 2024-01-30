package com.asset.foundation.contcatUs;

import com.asset.foundation.user.Status;
import com.asset.foundation.user.User;
import com.asset.foundation.utility.DateUtils;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    ContactUsRepository contactRepository;

    @Override
    public String save(RedirectAttributes redirectAttributes, ContactUsDto eventDto) {
        ContactUS event = toEntity(eventDto);
        contactRepository.save(event);
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
        return "redirect:/contactus";
    }

    @Override
    public String edit(RedirectAttributes redirectAttribute, ContactUsDto eventDto) {
        ContactUS event = toEntity(eventDto);
        contactRepository.save(event);
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.EDIT_SUCCESS.getValue());
        return "redirect:/auth/admin/event/list";
    }

    @Override
    public String delete(RedirectAttributes redirectAttribute, Long id) {
    	contactRepository.findById(id).ifPresent(event -> contactRepository.delete(event));
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.DELETE_SUCCESS.getValue());
        return "redirect:/auth/admin/event/list";
    }

    @Override
    public List<ContactUsDto> findAll() {
        List<ContactUS> contactUS = contactRepository.findAll();
        return toDto(contactUS);
    }

    @Override
    public ContactUsDto findById(Long id) {
        return toDto(contactRepository.findById(id).orElse(null));
    }

    public ContactUS toEntity(ContactUsDto dto) {
        if (dto == null) {
            return null;
        }
        ContactUS entity;
        entity = new ContactUS();
        toEntity(dto, entity);
        return entity;
    }

    public ContactUS toEntity(ContactUsDto dto, ContactUS entity) {
        if (entity == null || dto == null) {
            return null;
        }
        entity.setFullname(dto.getFullname());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setSubject(dto.getSubject());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public ContactUsDto toDto(ContactUS entity) {
        if (entity == null) {
            return null;
        }
        ContactUsDto dto = new ContactUsDto();
       
        dto.setFullname(entity.getFullname());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSubject(entity.getSubject());
        dto.setMessage(entity.getMessage());
        return dto;
    }

    public List<ContactUsDto> toDto(List<ContactUS> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
