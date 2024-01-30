package com.asset.foundation.subAdmin;

import com.asset.foundation.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/auth/admin")
public class SubAdminController {

    @Autowired
    SubAdminFacade subAdminFacade;

    @GetMapping("/subAdmin/list")
    public String saveSubAdminList(Model model) {
        return subAdminFacade.fetchListSubAdminPage(model);
    }

    @GetMapping("/subAdmin/add")
    public String getSubAdminList(Model model) {
        return subAdminFacade.fetchAddSubAdminPage(model);
    }

    @GetMapping("/subAdmin/edit/{id}")
    public String editSubAdmin(@PathVariable("id") Long id, Model model) {
        return subAdminFacade.fetchEditSubAdminPage(id, model);
    }

    @PostMapping("/subAdmin/add")
    public String saveSubAdmin(RedirectAttributes redirectAttributes, SubAdminDTO subAdminDTO, UserDTO userDto) {
        return subAdminFacade.saveSubAdmin(redirectAttributes, subAdminDTO, userDto);
    }

    @PostMapping("/subAdmin/edit")
    public String editSubAdmin(RedirectAttributes redirectAttribute, SubAdminDTO subAdminDTO,
                             UserDTO userDTO, @ModelAttribute("userId") Long uid) {
        return subAdminFacade.editSubAdmin(redirectAttribute, subAdminDTO, userDTO, uid);
    }

    @PostMapping("/subAdmin/delete/{subAdminId}")
    public String editSubAdmin(RedirectAttributes redirectAttribute, @PathVariable("subAdminId") Long uid) {
        return subAdminFacade.deactivateSubAdmin(redirectAttribute, uid);
    }


}
