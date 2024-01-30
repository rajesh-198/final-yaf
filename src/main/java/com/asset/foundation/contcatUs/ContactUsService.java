package com.asset.foundation.contcatUs;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface ContactUsService {

    String save(RedirectAttributes redirectAttributes, ContactUsDto eventDto);

    String edit(RedirectAttributes redirectAttributes, ContactUsDto eventDto);
    String delete(RedirectAttributes redirectAttributes, Long id);

    List<ContactUsDto> findAll();

    ContactUsDto findById(Long id);

}
