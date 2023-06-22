package com.rungroop.webmvc.controller;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    @Autowired
    ClubService clubService;

    @GetMapping("/clubs")
    public String getAllClub(Model model){

        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

}
