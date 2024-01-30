package com.asset.foundation.contcatUs;

import com.asset.foundation.subAdmin.SubAdminDTO;
import com.asset.foundation.subAdmin.SubAdminError;
import com.asset.foundation.user.UserDTO;
import com.asset.foundation.user.UserError;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import com.asset.foundation.utility.abstractclass.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ContactUsFacade {

    @Autowired
    ContactUsService eventService;

    public String saveEvent(RedirectAttributes redirectAttributes, ContactUsDto contactDto) {
            eventService.save(redirectAttributes, contactDto);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
            return "redirect:/contactus";
    }
}
