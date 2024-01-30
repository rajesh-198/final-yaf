package com.asset.foundation.program;

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

public class ProgramController {

    @Autowired
    ProgramService programService;

    @GetMapping("/program/list")
    public String getprogramListPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_PROGRAM, programService.findAll());
        return "program/list";
    }

    @GetMapping("/program/add")
    public String getprogramAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "program/add";
    }

    @GetMapping("/program/edit/{id}")
    public String editprogramPage(@PathVariable("id") Long id, Model model) {
        ProgramDto programDto = programService.findById(id);
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.EDIT.getValue());
        model.addAttribute(ParameterConstants.PARAM_PROGRAM, programDto);
        return "program/add";
    }

    @PostMapping("/program/add")
    public String saveprogram(RedirectAttributes redirectAttributes, ProgramDto programDto) {
        return programService.save(redirectAttributes, programDto);
    }

    @PostMapping("/program/edit")
    public String editprogram(RedirectAttributes redirectAttribute, ProgramDto programDto) {
        return programService.edit(redirectAttribute, programDto);
    }

    @PostMapping("/program/delete/{id}")
    public String deleteprogram(RedirectAttributes redirectAttribute, @PathVariable("id") Long uid) {
        return programService.delete(redirectAttribute, uid);
    }


}
