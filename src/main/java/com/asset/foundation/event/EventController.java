package com.asset.foundation.event;

import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/auth/admin")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/event/list")
    public String getEventListPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_EVENT, eventService.findAll());
        return "event/list";
    }

    @GetMapping("/event/add")
    public String getEventAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "event/add";
    }

    @GetMapping("/event/edit/{id}")
    public String editEventPage(@PathVariable("id") Long id, Model model) {
        EventDto eventDto = eventService.findById(id);
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.EDIT.getValue());
        model.addAttribute(ParameterConstants.PARAM_EVENT, eventDto);
        return "event/add";
    }

    @PostMapping("/event/add")
    public String saveEvent(RedirectAttributes redirectAttributes, EventDto eventDto) {
        return eventService.save(redirectAttributes, eventDto);
    }

    @PostMapping("/event/edit")
    public String editEvent(RedirectAttributes redirectAttribute, EventDto eventDto) {
        return eventService.edit(redirectAttribute, eventDto);
    }

    @PostMapping("/event/delete/{id}")
    public String deleteEvent(RedirectAttributes redirectAttribute, @PathVariable("id") Long uid) {
        return eventService.delete(redirectAttribute, uid);
    }


}
