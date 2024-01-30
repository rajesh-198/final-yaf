package com.asset.foundation.event;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface EventService {

    String save(RedirectAttributes redirectAttributes, EventDto eventDto);

    String edit(RedirectAttributes redirectAttributes, EventDto eventDto);
    String delete(RedirectAttributes redirectAttributes, Long id);

    List<EventDto> findAll();

    EventDto findById(Long id);

}
