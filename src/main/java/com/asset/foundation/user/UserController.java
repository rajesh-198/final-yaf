package com.asset.foundation.user;


import com.asset.foundation.subAdmin.SubAdminService;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.configuration.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SubAdminService subAdminService;

    @Autowired
    SecurityUtility securityUtility;

    @Autowired
    ChangePasswordValidation changePasswordValidation;

    @GetMapping("/auth/admin/dashboard")
    public String dashboard(Model model){
        model.addAttribute(ParameterConstants.PARAM_USER,userService.findAll().size());
        model.addAttribute(ParameterConstants.PARAM_SUB_ADMIN, subAdminService.findAllWithStatus());
        return "index";
    }

    @GetMapping("/changepassword")
    public String getChangePassword(){
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String setChangePassword(ChangePasswordDTO changePasswordDTO, RedirectAttributes redirectAttributes){
        ChangePasswordError changePasswordError=changePasswordValidation.validateChangePassword(changePasswordDTO);
        if (changePasswordError.getValid()){
            userService.changePassword(changePasswordDTO);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE,"Success");
            return "redirect:/logout";
        }else {
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, "Warning");
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_ERROR, changePasswordError);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_CHANGE_PASSWORD, changePasswordDTO);
            return "redirect:/changepassword";
        }
    }

}