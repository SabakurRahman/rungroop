package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.model.Club;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto clubDto);
}
