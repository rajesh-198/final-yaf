package com.asset.foundation.controller;

import com.asset.foundation.configuration.SecurityUtility;
import com.asset.foundation.donation.DonationService;
import com.asset.foundation.event.EventService;
import com.asset.foundation.program.ProgramDto;
import com.asset.foundation.program.ProgramService;
import com.asset.foundation.publication.FileStorageService;
import com.asset.foundation.subAdmin.SubAdminConverter;
import com.asset.foundation.user.User;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @Autowired
    SecurityUtility securityUtility;

    @Autowired
    SubAdminConverter subAdminConverter;

    @Autowired
    ProgramService programService;
    @Autowired
    DonationService donationService;

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String getHome(Model model) {
    	model.addAttribute(ParameterConstants.PARAM_PROGRAM, programService.findAll());
    	model.addAttribute(ParameterConstants.PARAM_DONATION, donationService.findAll());
        return "home/index";
    }
    @GetMapping("/donors")
    public String getDonar(Model model) {
    	model.addAttribute(ParameterConstants.PARAM_DONATION, donationService.findAll());
        return "home/donors";
    }
    @GetMapping("/about")
    public String getabout() {
        return "home/about";
    }
    @GetMapping("/publication")
    public String getpublication(Model model) {
        model.addAttribute(ParameterConstants.PARAM_PUBLICATION, fileStorageService.findAll());
        return "home/publications";
    }
    @GetMapping("/event")
    public String getevent(Model model) {
        model.addAttribute(ParameterConstants.PARAM_EVENT, eventService.findAll());
        return "home/event";
    }
    @GetMapping("/publication-single/view/{id}")
    public String getpublicationSingle() {
        return "home/publication-single";
    }
    @GetMapping("/donate")
    public String getdonate() {
        return "donation/paypal";
    }
    @GetMapping("/programs")
    public String getPrograms(Model model) {
        model.addAttribute(ParameterConstants.PARAM_PROGRAM, programService.findAll());
        return "home/programs";
    }
    @GetMapping("/programs-single/{id}")
    public String getProgramsSingle(@PathVariable("id") Long id, Model model) {
        ProgramDto programDto = programService.findById(id);
        model.addAttribute("imageLink", "/img/image-asset.jpeg");
        model.addAttribute(ParameterConstants.PARAM_PROGRAM, programDto);
        return "home/programs-single";
    }
    @GetMapping("/contactus")
    public String getcontact() {
        return "home/contact";
    }
    
    

    @GetMapping("/blank")
    public String getBlank() {
        return "blank";
    }

    @GetMapping("/login")
    public String login() {
        try {
            User user = securityUtility.getSecurity();
            if (user.getUserType().name().equalsIgnoreCase("SYSTEM_ADMIN"))
                return "redirect:/auth/admin/dashboard";
            else if(user.getUserType().name().equalsIgnoreCase("ADMIN"))
                return "redirect:/auth/dashboard";
            else
                return "redirect:/404";
        } catch (Exception e) {
            return "login";
        }
    }
    
    @GetMapping("/404")
    public String getEmptyPage(){
        return "404";
    }

    @GetMapping("/auth/dashboard")
    public String getPlayerDashboard(Model model) {
        return "subadminindex";
    }


}
