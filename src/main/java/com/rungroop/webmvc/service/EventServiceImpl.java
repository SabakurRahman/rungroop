package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.EventDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.model.Event;
import com.rungroop.webmvc.repository.ClubRepository;
import com.rungroop.webmvc.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
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

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event) -> mapEventToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByeventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapEventToEventDto(event);
    }

    private EventDto mapEventToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .Id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .build();
        return eventDto;

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
