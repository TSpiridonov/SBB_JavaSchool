package ru.javaschool.sbb.service.impl;

import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.PassengerDAO;
import ru.javaschool.sbb.DTO.PassengerDTO;
import ru.javaschool.sbb.entity.Passenger;
import ru.javaschool.sbb.mapper.PassengerMapper;
import ru.javaschool.sbb.service.api.PassengerService;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {


    private final PassengerDAO passengerDAO;
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerDTO findPassengerByPersonalData(String name, String surname, @Past Date birthDate) {
        Passenger passenger = passengerDAO.getPassengerByPersonalData(name, surname, birthDate);
        return passenger != null ? passengerMapper.toDTO(passenger) : null;
    }

    @Override
    public PassengerDTO getPassenger(int id) {
        return passengerMapper.toDTO(passengerDAO.getPassenger(id));
    }

    @Override
    @Transactional
    public void addPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerDAO.addPassenger(passenger);
    }

    @Override
    public void editPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerDAO.editPassenger(passenger);
    }

    @Override
    public void deletePassenger(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passengerDAO.deletePassenger(passenger);
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerMapper.toDTOList(passengerDAO.getAllPassengers());
    }


}
