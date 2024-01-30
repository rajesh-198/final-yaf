package com.asset.foundation.donation;

import com.asset.foundation.event.EventDto;
import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/donation")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping("/add")
    public String getDonationAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "donation/add";
    }

    @GetMapping("/paypal")
    public String getPaypalAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "donation/paypal";
    }

    @PostMapping("/add")
    public String saveDonation(RedirectAttributes redirectAttributes, DonationDto donationDto) {
        return donationService.save(redirectAttributes, donationDto);
    }

    @GetMapping("/list")
    public String getDonationListPage(Model model) {
       try {
    	model.addAttribute(ParameterConstants.PARAM_DONATION, donationService.findAll());
        return "donation/list";
       }
       catch(Exception ex){
    	   ex.getMessage();
       }
	 return "donation/list";
    }
}
