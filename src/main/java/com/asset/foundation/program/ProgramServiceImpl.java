package com.asset.foundation.program;

import com.asset.foundation.utility.ParameterConstants;
import com.asset.foundation.utility.abstractclass.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    ProgramRepository programRepository;

    @Override
    public String save(RedirectAttributes redirectAttributes, ProgramDto programDto) {
        Program program = toEntity(programDto);
        programRepository.save(program);
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttributes.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.ADDED_SUCCESS.getValue());
        return "redirect:/auth/admin/program/list";
    }

    @Override
    public String edit(RedirectAttributes redirectAttribute, ProgramDto programDto) {
        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                Program program = toEntity(programDto);
                programRepository.save(program);
                redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
                redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.EDIT_SUCCESS.getValue());
                return "redirect:/auth/admin/program/list";
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
        return "redirect:/auth/admin/program/list";
    }


    @Override
    public String delete(RedirectAttributes redirectAttribute, Long id) {
        programRepository.findById(id).ifPresent(event -> programRepository.delete(event));
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_MESSAGE, ResponseMessage.SUCCESS.getValue());
        redirectAttribute.addFlashAttribute(ParameterConstants.PARAM_TEXT, ResponseMessage.DELETE_SUCCESS.getValue());
        return "redirect:/auth/admin/program/list";
    }

    @Override
    public List<ProgramDto> findAll() {
        return toDto(programRepository.findAll());
    }

    @Override
    public ProgramDto findById(Long id) {
        return toDto(programRepository.findById(id).orElse(null));
    }

    public Program toEntity(ProgramDto dto) {
        if (dto == null) {
            return null;
        }
        Program entity;
        entity = new Program();
        toEntity(dto, entity);
        return entity;
    }

    public Program toEntity(ProgramDto dto, Program entity) {
        if (entity == null || dto == null) {
            return null;
        }
        if(dto.getId()!=null) {
            entity.setId(dto.getId());
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public ProgramDto toDto(Program entity) {
        if (entity == null) {
            return null;
        }
        ProgramDto dto = new ProgramDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public List<ProgramDto> toDto(List<Program> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
    }
}
