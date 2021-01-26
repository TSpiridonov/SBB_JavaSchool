package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.TrainDTO;
import ru.javaschool.sbb.service.api.TrainService;


@Component
public class TrainValidator implements Validator {

    @Autowired
    private TrainService trainService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TrainDTO trainDTO = (TrainDTO) o;

        if (trainService.findTrainByName(trainDTO.getTrainNumber()) != null) {
            errors.rejectValue("trainName", "Duplicate.trainName");
        }
    }
}
