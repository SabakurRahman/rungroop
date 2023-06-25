package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.EventDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.model.Event;
import com.rungroop.webmvc.repository.ClubRepository;
import com.rungroop.webmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class EventServiceImpl implements EventService{
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ClubRepository clubRepository;
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();

        Event event = mapEventDtoToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);

    }

    private Event mapEventDtoToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .Id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
        return event;
    }


}
