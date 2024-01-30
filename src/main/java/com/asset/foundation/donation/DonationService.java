package com.asset.foundation.donation;

import com.asset.foundation.event.EventDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface DonationService {


    String save(RedirectAttributes redirectAttributes, DonationDto donationDto);

    List<DonationDto> findAll();

    DonationDto findById(Long id);
}
