package com.rungroop.webmvc.controller;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/clubs/create")
    public  String createClube(Model model){
        Club club = new Club();
        model.addAttribute("club",club);
        return "create-club";
    }

    @PostMapping("/clubs/create")
    public String saveClub(@ModelAttribute("club") Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/edit")
    public  String editClub(@PathVariable("clubId") Long clubId,Model model){
       ClubDto clubDto = clubService.findClubById(clubId);
       model.addAttribute("clubDto",clubDto);
        return "club-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId")Long clubId,@ModelAttribute("clubDto") ClubDto clubDto){
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";

    }

}
