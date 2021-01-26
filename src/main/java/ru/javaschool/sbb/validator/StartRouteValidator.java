package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.RouteDTO;
import ru.javaschool.sbb.service.api.TripService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class StartRouteValidator implements Validator {

    @Autowired
    private TripService tripService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RouteDTO routeDTO = (RouteDTO) o;

        try {

            if (LocalDateTime.parse(routeDTO.getDepartureDate()).compareTo(LocalDateTime.now().plusMinutes(5)) <= 0) {
                errors.rejectValue("departureDate", "Invalid.time.current");
            }

            if (LocalDateTime.parse(routeDTO.getDepartureDate())
                    .compareTo(LocalDateTime.parse(routeDTO.getDeclaredArrivalDate())) >= 0) {
                errors.rejectValue("declaredArrivalDate", "Invalid.arrivalTime");

            } else if (!isRouteDurationValid(routeDTO.getDepartureDate(), routeDTO.getDeclaredArrivalDate())) {
                errors.rejectValue("declaredArrivalDate", "Invalid.route.duration");
            }

            if (!tripService.isTrainAvailableForSuchTrip(routeDTO.getTrainId(), routeDTO.getDepartureDate(),
                    routeDTO.getDeclaredArrivalDate())) {
                errors.rejectValue("trainId", "Train.is.unavailable");
            }
        } catch (DateTimeParseException dpe) {
            errors.rejectValue("departureDate", "Invalid.dateTime");
            errors.rejectValue("declaredArrivalDate", "Invalid.dateTime");
        }

    }


    private boolean isRouteDurationValid(String departureTime, String arrivalTime) {
        return (Timestamp.valueOf(LocalDateTime.parse(arrivalTime)).getTime()
                - Timestamp.valueOf(LocalDateTime.parse(departureTime)).getTime()) >= 900_000;
    }
}
