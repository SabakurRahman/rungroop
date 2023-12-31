package com.rungroop.webmvc.controller;

import com.rungroop.webmvc.dto.ClubDto;
import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.model.UserEntity;
import com.rungroop.webmvc.repository.UserEntityRepository;
import com.rungroop.webmvc.security.SecurityUtil;
import com.rungroop.webmvc.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    @Autowired
    ClubService clubService;
    @Autowired
    UserEntityRepository userEntityRepository;

    @GetMapping("/clubs")
    public String getAllClub(Model model){
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if(username !=null){
             user = userEntityRepository.findByEmail(username);
             model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
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
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("club",clubDto);
            return "create-club";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";

    }

    @GetMapping("/clubs/{clubId}/edit")
    public  String editClub(@PathVariable("clubId") Long clubId,Model model){
       ClubDto clubDto = clubService.findClubById(clubId);
       model.addAttribute("clubDto",clubDto);
        return "club-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId")Long clubId, @Valid
                             @ModelAttribute("clubDto") ClubDto clubDto,
                             BindingResult result,Model model
                             ){

            if(result.hasErrors()) {
                model.addAttribute("clubDto",clubDto);
                return "club-edit";
            }

        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";

    }

    @GetMapping("/clubDetails/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId,Model model){
        UserEntity user = new UserEntity();
        ClubDto clubDto=clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userEntityRepository.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);

        model.addAttribute("club",clubDto);
        return "club-details";
    }

    @GetMapping("/delete/club/{clubId}")
    public String deteleClub(@PathVariable("clubId") Long clubId){
        clubService.deleteClubeById(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/search/clubs")
    public String serchClubByTitle(@RequestParam(value = "query") String query ,Model model){
        List<ClubDto> clubs = clubService.findByClubTitle(query);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }
    @GetMapping("/test")
    @ResponseBody
    public String testDevTools(){
        return "Hello Word Test For Dvetools Final Test time test";

    }

}
