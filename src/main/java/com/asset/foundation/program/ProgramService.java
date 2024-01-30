package com.asset.foundation.program;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface ProgramService {

    String save(RedirectAttributes redirectAttributes, ProgramDto eventDto);

    String edit(RedirectAttributes redirectAttributes, ProgramDto eventDto);
    String delete(RedirectAttributes redirectAttributes, Long id);

    List<ProgramDto> findAll();

    ProgramDto findById(Long id);

}
