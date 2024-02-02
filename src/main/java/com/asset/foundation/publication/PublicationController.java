package com.asset.foundation.publication;


import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


@Controller
@RequestMapping(value = "/publication")
public class PublicationController {

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/list")
    public String getEventListPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_PUBLICATION, storageService.findAll());
        return "/publication/list";
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewPdf(@PathVariable Long id) {
        byte[] pdfData = storageService.getPdfDataById(id);
        if (pdfData != null) {

            ByteArrayResource resource = new ByteArrayResource(pdfData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=example.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        }
        return null;
    }

    @GetMapping("/add")
    public String getEventAddPage(Model model) {
        model.addAttribute(ParameterConstants.PARAM_ACTION, OperationType.ADD.getValue());
        return "/publication/add";
    }


    @PostMapping("/add")
    public String savePublication(RedirectAttributes redirectAttributes, PublicationDto publicationDto) {
        return storageService.save(redirectAttributes, publicationDto);
    }

//    @PostMapping("publication/upload/{file}")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        try {
//            storageService.store(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            System.out.print(message);
//
//            return "/publication/list";
//
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return "/publication/list";
//
//        }
//
//    }

//    @GetMapping("/files")
//    public ResponseEntity<List<ResponseFile>> getListFiles() {
//        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
////          .path(dbFile.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

//    @GetMapping("/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//        Publication publication = storageService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + publication.getName() + "\"")
//                .body(publication.getData());
//    }
}