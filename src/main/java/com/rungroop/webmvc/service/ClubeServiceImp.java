package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubeServiceImp implements ClubService{

    @Autowired
    private ClubRepository clubRepository;


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
        Club club = mapToclubDtoToClub(clubDto);
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
        Club club = mapToclubDtoToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteClubeById(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    private Club mapToclubDtoToClub(ClubDto clubDto) {
        Club club = Club.builder().Id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    private ClubDto mapToClubToDto(Club club) {

        ClubDto clubDto = ClubDto.builder().Id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .createdOn(club.getCreatedOn())
                .createdOn(club.getUpdatedOn())
                .build();

        return clubDto;
    }

}
