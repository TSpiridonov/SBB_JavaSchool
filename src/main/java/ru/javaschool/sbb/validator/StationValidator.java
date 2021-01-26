package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.service.api.StationService;


@Component
public class StationValidator implements Validator {

    @Autowired
    private StationService stationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StationDTO stationDTO = (StationDTO) o;

        if (stationService.findStationDTOByTitle(stationDTO.getTitle()) != null) {
            errors.rejectValue("title", "Duplicate.stationTitle");
        }

    }
}

