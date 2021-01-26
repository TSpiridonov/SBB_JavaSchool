package ru.javaschool.sbb.mapper;

import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.TrainDTO;
import ru.javaschool.sbb.entity.Train;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainMapper{

    public Train toEntity(TrainDTO trainDTO) {
        if ( trainDTO == null ) {
            return null;
        }

        Train train = new Train();

        train.setId( trainDTO.getId() );
        train.setTrainNumber( trainDTO.getTrainNumber() );
        train.setCapacity( trainDTO.getCapacity() );

        return train;
    }

    public TrainDTO toDTO(Train train) {
        if ( train == null ) {
            return null;
        }

        TrainDTO trainDTO =  new TrainDTO();

        trainDTO.setId( train.getId() );
        trainDTO.setTrainNumber( train.getTrainNumber() );
        trainDTO.setCapacity( train.getCapacity() );

        return trainDTO;
    }

    public List<TrainDTO> toDTOList(List<Train> trainList) {
        if ( trainList == null ) {
            return null;
        }

        List<TrainDTO> list = new ArrayList<TrainDTO>( trainList.size() );
        for ( Train train : trainList ) {
            list.add( toDTO( train ) );
        }

        return list;
    }

     public List<Train> toEntityList(List<TrainDTO> trainDTOList) {
        if ( trainDTOList == null ) {
            return null;
        }

        List<Train> list = new ArrayList<Train>( trainDTOList.size() );
        for ( TrainDTO trainDTO : trainDTOList ) {
            list.add( toEntity( trainDTO ) );
        }

        return list;
    }
}
