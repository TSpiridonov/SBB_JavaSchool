package ru.javaschool.sbb.DAO.api;

import jakarta.validation.constraints.Past;
import ru.javaschool.sbb.entity.Passenger;

import java.util.Date;
import java.util.List;

public interface PassengerDAO {

    Passenger getPassenger(int id);

    List<Passenger> getAllPassengers();

    void addPassenger(Passenger passenger);

    void editPassenger(Passenger passenger);

    void deletePassenger(Passenger passenger);



    Passenger getPassengerByPersonalData(String name, String surname, @Past Date birthDate);


}
