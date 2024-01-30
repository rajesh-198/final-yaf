package com.asset.foundation.controller.rest;

import com.asset.foundation.subAdmin.SubAdminDTO;
import com.asset.foundation.subAdmin.SubAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SubAdminResource {

    @Autowired
    ResponseDetail responseDetail;

    @Autowired
    private SubAdminService subAdminService;

    @GetMapping("/sub-admin")
    public ResponseEntity<ResponseDTO> getCategory() {
        ResponseDTO responseDTO;
        List<SubAdminDTO> subAdminDTOList = subAdminService.findAllWithStatus();
        if (subAdminDTOList != null) {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.SUCCESS, "Successfully retrieved Player list", subAdminDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO = responseDetail.getResponseDTO(ResponseStatus.BAD_REQUEST, "Unable to retrieve Player list", null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
