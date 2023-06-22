package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.ClubDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClubService {
    List<ClubDto> findAllClubs();
}
