package com.asset.foundation.publication;

import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class FileStorageService {

    @Autowired
    private PublicationRepository publicationRepository;

    public byte[] getPdfDataById(Long id) {
        PublicationDto publicationDto = toDto(publicationRepository.findById(id).orElse(null));
        if (publicationDto != null && publicationDto.getDataBytes() != null) {
            return publicationDto.getDataBytes();
        }
        return null;
    }

    public Publication store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Publication Publication = new Publication(fileName, file.getContentType(), file.getBytes());

        return publicationRepository.save(Publication);
    }

    public Publication getFile(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    public Stream<Publication> getAllFiles() {
        return publicationRepository.findAll().stream();
    }

    public List<PublicationDto> findAll() {
        return toDto(publicationRepository.findAll());
    }


    public String save(RedirectAttributes redirectAttribute, PublicationDto publicationDto) {
        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                Publication publication = toEntity(publicationDto);
                publicationRepository.save(publication);
                redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, com.asset.foundation.utility.abstractclass.ResponseMessage.SUCCESS.getValue());
                redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.EDIT_SUCCESS.getValue());
                return "redirect:/publication/list";
            } catch (ObjectOptimisticLockingFailureException e) {
                // Increment retry count and retry
                retryCount++;
                System.out.println("Optimistic locking failure. Retrying... Retry count: " + retryCount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println("Operation failed after max retries.");
        return "redirect:/publication/list";
    }

    public Publication toEntity(PublicationDto dto) throws IOException {
        if (dto == null) {
            return null;
        }
        Publication entity;
        entity = new Publication();
        toEntity(dto, entity);
        return entity;
    }

    public Publication toEntity(PublicationDto dto, Publication entity) throws IOException {
        if (entity == null || dto == null) {
            return null;
        }
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setType(dto.getData().getOriginalFilename());
        entity.setData(convertMultipartFileToBytes(dto.getData()));
        return entity;
    }

    public static byte[] convertMultipartFileToBytes(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }

    public PublicationDto toDto(Publication entity) {
        if (entity == null) {
            return null;
        }
        PublicationDto dto = new PublicationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setDataBytes(entity.getData());
        return dto;
    }

    public List<PublicationDto> toDto(List<Publication> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }

}