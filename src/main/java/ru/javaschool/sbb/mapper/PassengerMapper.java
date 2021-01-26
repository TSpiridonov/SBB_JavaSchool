package ru.javaschool.sbb.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.PassengerDTO;
import ru.javaschool.sbb.entity.Passenger;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerMapper {

    public Passenger toEntity(PassengerDTO passengerDTO) {
        if ( passengerDTO == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        passenger.setId( passengerDTO.getId() );
        passenger.setName( passengerDTO.getName() );
        passenger.setSurname( passengerDTO.getSurname() );
        passenger.setBirthDate( passengerDTO.getBirthDate() );

        return passenger;
    }

    public PassengerDTO toDTO(Passenger passenger) {
        if ( passenger == null ) {
            return null;
        }

        PassengerDTO passengerDTO = new PassengerDTO();

        passengerDTO.setId( passenger.getId() );
        passengerDTO.setName( passenger.getName() );
        passengerDTO.setSurname( passenger.getSurname() );
        passengerDTO.setBirthDate( passenger.getBirthDate() );

        return passengerDTO;
    }

    public List<PassengerDTO> toDTOList(List<Passenger> passengers) {
        if ( passengers == null ) {
            return null;
        }

        List<PassengerDTO> list = new ArrayList<PassengerDTO>( passengers.size() );
        for ( Passenger passenger : passengers ) {
            list.add( toDTO( passenger ) );
        }

        return list;
    }
}
