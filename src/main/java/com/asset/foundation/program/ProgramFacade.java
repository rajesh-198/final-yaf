package com.asset.foundation.program;

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
public class ProgramFacade {

    @Autowired
    ProgramService programService;

    public String saveEvent(RedirectAttributes redirectAttributes, ProgramDto programDto) {
    	programService.save(redirectAttributes, programDto);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
            return "redirect:/auth/admin/subAdmin/list";
    }
}
