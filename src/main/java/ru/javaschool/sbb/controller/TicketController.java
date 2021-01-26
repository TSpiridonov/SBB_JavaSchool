package ru.javaschool.sbb.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.DTO.TicketInfo;
import ru.javaschool.sbb.service.api.TicketService;
import ru.javaschool.sbb.validator.PassengerValidator;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private PassengerValidator passengerValidator;


    @RequestMapping(value = "/checkin", method = { RequestMethod.POST, RequestMethod.GET})
    public ModelAndView checkIn(@Valid @ModelAttribute TicketDTO ticketDTO, BindingResult bindingResult,
                                @RequestParam String timeSearchFrom,
                                @RequestParam String timeSearchTo,
                                RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        passengerValidator.validate(ticketDTO,bindingResult);

        if (bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            redirectAttributes.addFlashAttribute("errors", errors);

            String redirectUrl = "redirect:/schedule?stationFrom=" + ticketDTO.getStationFromId()
                    + "&stationTo=" + ticketDTO.getStationToId()
                    + "&dateFrom=" + timeSearchFrom +
                    "&dateTo=" + timeSearchTo +
                    "&err";
            modelAndView.setViewName(redirectUrl);
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/alltickets?success");

        return modelAndView;

    }

    @GetMapping(value = "/alltickets")
    public ModelAndView getAllUserTickets() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<TicketInfo> ticketInfos = ticketService.getAllUserTickets(auth.getName());

        modelAndView.addObject("ticketInfos", ticketInfos);
        modelAndView.setViewName("UserTicketsPage");
        return modelAndView;
    }

}
