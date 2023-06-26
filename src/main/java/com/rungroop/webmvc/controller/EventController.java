package com.rungroop.webmvc.controller;

import com.rungroop.webmvc.dto.EventDto;
import com.rungroop.webmvc.model.Event;
import com.rungroop.webmvc.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/event/{clubId}/create")
    public String getCreateEvent(@PathVariable("clubId") Long clubId, Model model){
        Event eventDto = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", eventDto);

        return "create-event";
    }

    @PostMapping("/event/{clubId}/create")
    public  String postCreateEvent(@PathVariable("clubId") Long clubId,@Valid @ModelAttribute("event") EventDto eventDto,BindingResult result,Model model){

        if(result.hasErrors()) {
            model.addAttribute("event",eventDto);
            return "create-event";
        }

         eventService.createEvent(clubId,eventDto);
         return "redirect:/clubDetails/"+clubId;


    }

}
