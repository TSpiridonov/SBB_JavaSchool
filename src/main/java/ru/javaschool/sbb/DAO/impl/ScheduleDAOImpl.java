package ru.javaschool.sbb.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.ScheduleDAO;
import ru.javaschool.sbb.DTO.TimetableDTO;
import ru.javaschool.sbb.entity.Schedule;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where (s.departureTime between :tmp1 and :tmp2)" +
                        " and s.stationFrom = :stationFrom and s.trip.canceled = false order by s.departureTime")
                .setParameter("stationFrom", stationFrom)
                .setParameter("tmp1", dateFrom)
                .setParameter("tmp2", dateTo);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Schedule> getSchedulesByTripIdAndStationTo(int tripId, Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.trip.id =:tripId and s.stationTo = :stationTo")
                .setParameter("tripId", tripId)
                .setParameter("stationTo", stationTo);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Schedule> getSchedulesByStationFrom(Station stationFrom) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.stationFrom = :stationFrom order by s.departureTime")
                .setParameter("stationFrom", stationFrom);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Schedule> getSchedulesByStationTo(Station stationTo) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.stationTo = :stationTo order by s.arrivalTime")
                .setParameter("stationTo", stationTo);
        return query.getResultList();
    }

    @Override
    public List<Schedule> getSchedulesByTripId(int tripId) {
        Query query = entityManager
                .createQuery("select s from Schedule s where s.trip.id = :tripId")
                .setParameter("tripId", tripId);
        return query.getResultList();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        entityManager.persist(entityManager.merge(schedule));
    }

    @Override
    public void editSchedule(Schedule schedule) {
        entityManager.merge(schedule);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        entityManager.remove(entityManager.merge(schedule));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TimetableDTO> getDepartureTimetableByStationId(int stationId) {
        Query query = entityManager.createQuery("select " +
                "new TimetableDTO(s.trip.train.trainNumber, s.stationFrom.title, " +
                "s.trip.arrivalStation.title, s.departureTime, s.trip.arrivalTime) " +
                "from Schedule s where s.stationFrom.id =: stationId " +
                "and date(s.departureTime) = current_date order by s.departureTime");


        query.setParameter("stationId", stationId);

        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TimetableDTO> getArrivalTimetableByStationId(int stationId) {

        Query query = entityManager.createQuery("select " +
                "new TimetableDTO(s.trip.train.trainNumber, s.trip.departureStation.title, " +
                "s.stationTo.title, s.trip.departureTime, s.arrivalTime) " +
                "from Schedule s where s.stationTo.id =: stationId and date(s.arrivalTime) = current_date " +
                "order by s.arrivalTime");

        query.setParameter("stationId", stationId);

        return query.getResultList();
    }

}