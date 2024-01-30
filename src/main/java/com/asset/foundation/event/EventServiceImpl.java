package com.asset.foundation.event;

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
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public String save(RedirectAttributes redirectAttributes, EventDto eventDto) {
        Event event = toEntity(eventDto);
        eventRepository.save(event);
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
        return "redirect:/auth/admin/event/list";
    }

    @Override
    public String edit(RedirectAttributes redirectAttribute, EventDto eventDto) {
        Event event = toEntity(eventDto);
        eventRepository.save(event);
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.EDIT_SUCCESS.getValue());
        return "redirect:/auth/admin/event/list";
    }

    @Override
    public String delete(RedirectAttributes redirectAttribute, Long id) {
        eventRepository.findById(id).ifPresent(event -> eventRepository.delete(event));
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.DELETE_SUCCESS.getValue());
        return "redirect:/auth/admin/event/list";
    }

    @Override
    public List<EventDto> findAll() {
        return toDto(eventRepository.findAll());
    }

    @Override
    public EventDto findById(Long id) {
        return toDto(eventRepository.findById(id).orElse(null));
    }

    public Event toEntity(EventDto dto) {
        if (dto == null) {
            return null;
        }
        Event entity;
        entity = new Event();
        toEntity(dto, entity);
        return entity;
    }

    public Event toEntity(EventDto dto, Event entity) {
        if (entity == null || dto == null) {
            return null;
        }
        entity.setTitle(dto.getTitle());
        entity.setEventDate(DateUtils.stringToDate(dto.getEventDate()));
        entity.setLocation(dto.getLocation());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public EventDto toDto(Event entity) {
        if (entity == null) {
            return null;
        }
        EventDto dto = new EventDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setEventDate(DateUtils.DateToString(entity.getEventDate()));
        dto.setLocation(entity.getLocation());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<EventDto> toDto(List<Event> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
