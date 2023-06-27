package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    public void createEvent(Long clubId, EventDto eventDto);

   public List<EventDto> findAllEvents();

    EventDto findByeventId(Long eventId);

    void updateEvent(EventDto eventDto);
}
