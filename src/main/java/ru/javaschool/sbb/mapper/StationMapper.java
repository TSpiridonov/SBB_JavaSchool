package ru.javaschool.sbb.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Station;

import java.util.ArrayList;
import java.util.List;


@Component
public class StationMapper {

    public Station toEntity(StationDTO stationDTO) {
        if ( stationDTO == null ) {
            return null;
        }

        Station station = new  Station();

        station.setId( stationDTO.getId() );
        station.setTitle( stationDTO.getTitle() );

        return station;
    }

    public StationDTO toDTO(Station station) {
        if ( station == null ) {
            return null;
        }

        StationDTO stationDTO = new StationDTO();

        stationDTO.setId( station.getId() );
        stationDTO.setTitle( station.getTitle() );

        return stationDTO;
    }


    public List<StationDTO> toDTOList(List<Station> stationList) {
        if ( stationList == null ) {
            return null;
        }

        List<StationDTO> list = new ArrayList<StationDTO>( stationList.size() );
        for ( Station station : stationList ) {
            list.add( toDTO( station ) );
        }

        return list;
    }
    public List<Station> toEntityList(List<StationDTO> stationDTOList) {
        if ( stationDTOList == null ) {
            return null;
        }

        List<Station> list = new ArrayList<Station>( stationDTOList.size() );
        for ( StationDTO stationDTO : stationDTOList ) {
            list.add( toEntity( stationDTO ) );
        }

        return list;
    }
}
