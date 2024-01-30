package com.asset.foundation.utility;

import com.asset.foundation.configuration.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    SecurityUtility securityUtility;

//    @ModelAttribute("currentUser")
//    public User getCurrentUser(Authentication authentication) {
//        return (authentication == null) ? null : securityUtility.getSecurity();
//    }
//
//
//    @ModelAttribute("currentRole")
//    public String getCurrentRole(Authentication authentication) {
//        if (authentication == null)
//            return null;
//        else {
//            String string = null;
//            if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("SYSTEM_ADMIN"))
//                string = "systemadmin";
//            else if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("ADMIN"))
//                string = "admin";
//            return string;
//        }
//    }
//
//    @ModelAttribute("currentAgent")
//    public String getCurrentAgent(Authentication authentication) {
//        if (authentication == null)
//            return null;
//        else {
//            if (securityUtility.getSecurity().getUserType().name().equalsIgnoreCase("ADMIN"))
//                return securityUtility.getCurrentAgent().getAgentName();
//            else
//                return null;
//        }
//    }

}
