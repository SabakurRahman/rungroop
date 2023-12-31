package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.dto.EventDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.model.Event;
import com.rungroop.webmvc.model.UserEntity;
import com.rungroop.webmvc.repository.ClubRepository;
import com.rungroop.webmvc.repository.UserEntityRepository;
import com.rungroop.webmvc.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubeServiceImp implements ClubService{

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
       // List<ClubDto> clubDtoList = new ArrayList<>();
//        for (Club club : clubs) {
//            ClubDto clubDto = new ClubDto();
//            clubDto.setId(club.getId());
//            clubDto.setContent(club.getContent());
//            clubDto.setTitle(club.getTitle());
//            clubDto.setPhotoUrl(club.getPhotoUrl());
//            clubDto.setCreatedOn(club.getCreatedOn());
//            clubDto.setUpdatedOn(club.getUpdatedOn());
//            clubDtoList.add(clubDto);
//
//        }
        return clubs.stream().map((club) -> mapToClubToDto(club)).collect(Collectors.toList());
//        return clubDtoList;
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        System.out.println(username);
        UserEntity user = userEntityRepository.findByEmail(username);
        Club club = mapToclubDtoToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
        return null;
    }

    @Override
    public ClubDto findClubById(Long clubId) {
         Club club = clubRepository.findById(clubId).get();
         return mapToClubToDto(club);

    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        System.out.println(username);
        UserEntity user = userEntityRepository.findByEmail(username);
        Club club = mapToclubDtoToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void deleteClubeById(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> findByClubTitle(String query) {
        List<Club> clubs = clubRepository.searchClubByTitle(query);

        return clubs.stream().map((club) -> mapToClubToDto(club)).collect(Collectors.toList());
    }

    private Club mapToclubDtoToClub(ClubDto clubDto) {
        Club club = Club.builder().Id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .createdBy(clubDto.getCreatedBy())
                .build();
        return club;
    }

    private ClubDto mapToClubToDto(Club club) {

        ClubDto clubDto = ClubDto.builder().Id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .events(club.getEvents().stream().map((event) -> mapToEventToDto(event)).collect(Collectors.toList()))
                .createdOn(club.getCreatedOn())
                .createdOn(club.getUpdatedOn())
                .createdBy(club.getCreatedBy())
                .build();

        return clubDto;
    }

    private EventDto mapToEventToDto(Event event) {
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

}
