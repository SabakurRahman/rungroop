package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.EventDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    public void createEvent(Long clubId, EventDto eventDto);
}
