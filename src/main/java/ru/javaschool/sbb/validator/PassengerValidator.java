package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.service.api.PassengerService;


@Component
public class PassengerValidator implements Validator {

    @Autowired
    private PassengerService passengerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TicketDTO.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {

        TicketDTO ticketDTO = (TicketDTO) o;

        if(ticketDTO.getPassengerDTO().getBirthDate() == null){
            errors.rejectValue("passengerDTO.birthDate", "Invalid.birthdate",
                    "*Birthdate cannot be empty.");
        }

    }
}
