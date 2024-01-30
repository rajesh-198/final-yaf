package com.asset.foundation.subAdmin;

import com.asset.foundation.user.*;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import com.asset.foundation.utility.abstractclass.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class SubAdminFacade {

    @Autowired
    SubAdminValidation subAdminValidation;

    @Autowired
    SubAdminService subAdminService;

    @Autowired
    UserValidation userValidation;

    @Autowired
    UserService userService;

    public String fetchListSubAdminPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_SUB_ADMIN, subAdminService.findAll());
        return "subAdmin/list";
    }

    public String fetchAddSubAdminPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "subAdmin/add";
    }

    public String fetchEditSubAdminPage(Long id, Model model) {
        SubAdminDTO subAdminDTO = subAdminService.findPlayerDtoById(id);
        UserDTO userDTO = userService.findUserDtoById(subAdminDTO.getUserId());
        userDTO.setPassword("");
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.EDIT.getValue());
        model.addAttribute(ParameterConstants.PARAM_SUB_ADMIN, subAdminDTO);
        model.addAttribute(ParameterConstants.PARAM_USER, userDTO);
        return "subAdmin/add";
    }

    public String saveSubAdmin(RedirectAttributes redirectAttributes, SubAdminDTO subAdminDTO, UserDTO userDto) {
        SubAdminError subAdminError = subAdminValidation.validatePlayer(subAdminDTO, OperationType.ADD.getValue());
        UserError userError = userValidation.validateUser(userDto, OperationType.ADD.getValue());
        if (subAdminError.getValid() && userError.getValid()) {
            subAdminService.saveDto(userDto, subAdminDTO);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
            return "redirect:/auth/admin/subAdmin/list";
        } else {
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_ERROR, subAdminError);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_USER_ERROR, userError);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_SUB_ADMIN, subAdminDTO);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_USER, userDto);
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_VALID, OperationType.SHOW.getValue());
            redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.PLAYER_NOT_ADDED.getValue());
            return "redirect:/auth/admin/subAdmin/add";
        }
    }

    public String editSubAdmin(RedirectAttributes redirectAttribute, SubAdminDTO subAdminDTO,
                             UserDTO userDTO, @ModelAttribute("userId") Long uid) {
        SubAdminError subAdminError = subAdminValidation.validatePlayer(subAdminDTO, OperationType.EDIT.getValue());
        subAdminDTO.setUserId(uid);
        userDTO.setId(uid);
        UserError userError = userValidation.validateUser(userDTO, OperationType.EDIT.getValue());
        if (subAdminError.getValid() && userError.getValid()) {
            subAdminService.editDto(userDTO, subAdminDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.EDIT_SUCCESS.getValue());
            return "redirect:/auth/admin/subAdmin/list";
        } else {
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_ERROR, subAdminError);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_USER_ERROR, userError);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_SUB_ADMIN, subAdminDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_USER, userDTO);
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_VALID, OperationType.SHOW.getValue());
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.PLAYER_NOT_EDITED);
            return "redirect:/auth/admin/subAdmin/edit/" + subAdminDTO.getId();
        }
    }

    public String deactivateSubAdmin(RedirectAttributes redirectAttribute, @ModelAttribute("playerId") Long playerId) {
        SubAdmin subAdmin = subAdminService.deletePlayer(playerId);
        if (subAdmin != null) {
            User user = userService.deleteUser(subAdmin.getUser());
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, user.getStatus().equals(Status.ACTIVE) ? ResponseMessage.ACTIVATED_SUCCESS.getValue() : ResponseMessage.DELETE_SUCCESS.getValue());
            return "redirect:/auth/admin/subAdmin/list";
        } else {
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.ERROR.getValue());
            redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.DELETE_FAIL.getValue());
            return "redirect:/auth/admin/subAdmin/list";
        }
    }

}
