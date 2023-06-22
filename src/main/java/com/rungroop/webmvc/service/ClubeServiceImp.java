package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ClubeServiceImp implements ClubService{

    @Autowired
    private ClubRepository clubRepository;


    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        List<ClubDto> clubDtoList = new ArrayList<>();
        for (Club club : clubs) {
            ClubDto clubDto = new ClubDto();
            clubDto.setId(club.getId());
            clubDto.setContent(club.getContent());
            clubDto.setTitle(club.getTitle());
            clubDto.setPhotoUrl(club.getPhotoUrl());
            clubDto.setCreatedOn(club.getCreatedOn());
            clubDto.setUpdatedOn(club.getUpdatedOn());
            clubDtoList.add(clubDto);

        }
        return clubDtoList;
    }

}
