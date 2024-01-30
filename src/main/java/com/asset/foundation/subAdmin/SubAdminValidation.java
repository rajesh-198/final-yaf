package com.asset.foundation.subAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubAdminValidation {

    @Autowired
    SubAdminService subAdminService;

    public SubAdminError validatePlayer(SubAdminDTO subAdminDTO, String type) {
        SubAdminError subAdminError = new SubAdminError();
        boolean valid = true;
//        if (type.equalsIgnoreCase("add")) {
//            if (playerService.findAllWithStatus().size() >= 15) {
//                valid = false;
//                playerError.setSize("Player limit Exceeds!!!");
//            }
//        }
        // if (playerDTO.getAgentName() == null ||
        // playerDTO.getAgentName().equals("") ||
        // playerDTO.getAgentName().replaceAll(" ", "").equals("") ||
        // playerDTO.getAgentName().trim().equals("")) {
        // valid = false;
        // playerError.setAgentName("Invalid Player name!");
        // } else if (playerDTO.getAgentName().replaceAll(" ", "").length() <= 4) {
        // valid = false;
        // playerError.setAgentName("Player name too short!");
        // } else if (playerDTO.getAgentName().replaceAll(" ", "").length() >= 20) {
        // valid = false;
        // playerError.setAgentName("Player name too long!");
        // }

// if (type.equalsIgnoreCase("add")) {
// if (playerService.findByAgentNameAndStatus(playerDTO.getAgentName(), Status.ACTIVE) != null) {
// valid = false;
// playerError.setAgentName("Player already exists!");
// }
// }

// if (type.equalsIgnoreCase("edit")) {
// if (!playerService.findAgentDtoById(playerDTO.getId()).getAgentName()
// .equalsIgnoreCase(playerDTO.getAgentName())) {
// if (playerService.findByAgentNameAndStatus(playerDTO.getAgentName(), Status.ACTIVE) != null) {
// valid = false;
// playerError.setAgentName("Player already exists!");
// }
// }
// }

//        if (playerDTO.getPlayerCode() == null ||
//                playerDTO.getPlayerCode().equals("") ||
//                playerDTO.getPlayerCode().replaceAll(" ", "").equals("") ||
//                playerDTO.getPlayerCode().trim().equals("")) {
//            valid = false;
//            playerError.setPlayerCode("Invalid Player code!");
//        } else if (playerDTO.getPlayerCode().replaceAll(" ", "").length() <= 2) {
//            valid = false;
//            playerError.setPlayerCode("Player code too short!");
//        } else if (playerDTO.getPlayerCode().replaceAll(" ", "").length() >= 7) {
//            valid = false;
//            playerError.setPlayerCode("Player code too long!");
//        }
//
//        if (playerDTO.getAddress() == null ||
//                playerDTO.getAddress().equals("") ||
//                playerDTO.getAddress().replaceAll(" ", "").equals("") ||
//                playerDTO.getAddress().trim().equals("")) {
//            valid = false;
//            playerError.setAddress("Invalid address code!");
//        } else if (playerDTO.getAddress().replaceAll(" ", "").length() <= 2) {
//            valid = false;
//            playerError.setAddress("Address too short!");
//        } else if (playerDTO.getAddress().replaceAll(" ", "").length() >= 15) {
//            valid = false;
//            playerError.setAddress("Address too long!");
//        }
//
//        if (playerDTO.getDob() == null ||
//                playerDTO.getDob().equals("") ||
//                playerDTO.getDob().replaceAll(" ", "").equals("") ||
//                playerDTO.getDob().trim().equals("")) {
//            valid = false;
//            playerError.setDob("Invalid Date of Birth!");
//        } else {
//
//            SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
//            sdfrmt.setLenient(false);
//            try {
//                sdfrmt.parse(playerDTO.getDob());
//            } catch (ParseException e) {
//                valid = false;
//                playerError.setDob("Invalid Date format!,MM/dd/yyyy is a valid date format.");
//            }
//        }
//
//        if (playerDTO.getMobileNumber() == null ||
//                playerDTO.getMobileNumber().isEmpty()) {
//            valid = false;
//            playerError.setMobileNumber(Collections.singletonList("Invalid Mobile Number!"));
//        }

        subAdminError.setValid(valid);
        return subAdminError;
    }


}
