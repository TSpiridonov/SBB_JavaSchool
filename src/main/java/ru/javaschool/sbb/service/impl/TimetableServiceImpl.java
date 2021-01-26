package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.ScheduleDAO;
import ru.javaschool.sbb.DTO.TimetableDTO;
import ru.javaschool.sbb.service.api.TimetableService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final ScheduleDAO scheduleDAO;

    @Override
    @Transactional
    public List<TimetableDTO> getDepartureTimetable(Integer stationId) {
        return scheduleDAO.getDepartureTimetableByStationId(stationId);
    }

    @Override
    @Transactional
    public List<TimetableDTO> getArrivalTimetable(Integer stationId) {
        return scheduleDAO.getArrivalTimetableByStationId(stationId);
    }

}
