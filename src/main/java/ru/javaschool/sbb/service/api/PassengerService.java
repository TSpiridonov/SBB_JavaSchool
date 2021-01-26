package ru.javaschool.sbb.service.api;


import jakarta.validation.constraints.Past;
import ru.javaschool.sbb.DTO.PassengerDTO;

import java.util.Date;
import java.util.List;


public interface PassengerService {

    PassengerDTO findPassengerByPersonalData(String name, String surname, @Past Date birthDate);

    PassengerDTO getPassenger(int id);

    void addPassenger(PassengerDTO passengerDTO);

    void editPassenger(PassengerDTO passengerDTO);

    void deletePassenger(PassengerDTO passengerDTO);

    List<PassengerDTO> getAllPassengers();


}
