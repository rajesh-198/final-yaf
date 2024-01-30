package com.asset.foundation.contcatUs;


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
@RequestMapping("/contactUs")
public class ContactUsController {

    @Autowired
    ContactUsService contactService;

    @GetMapping("/list")
    public String getEventListPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_CONTACT, contactService.findAll());
        return "ContactUs/list";
    }
    @GetMapping("/add")
    public String getEventAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "/contactus";
    }
   
    
    @PostMapping("/add")
    public String saveEvent(RedirectAttributes redirectAttributes, ContactUsDto contactDto) {
    	redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE,"Success");
        return contactService.save(redirectAttributes, contactDto);
    }

   

    @PostMapping("/delete/{id}")
    public String deleteEvent(RedirectAttributes redirectAttribute, @PathVariable("id") Long uid) {
        return contactService.delete(redirectAttribute, uid);
    }


}