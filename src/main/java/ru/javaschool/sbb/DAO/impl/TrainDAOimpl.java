package ru.javaschool.sbb.DAO.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.TrainDAO;
import ru.javaschool.sbb.entity.Train;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TrainDAOimpl implements TrainDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Train getTrain(int id) {
        return entityManager.find(Train.class, id);
    }

    @Override
    public Train getTrainByNumber(String trainNumber) {

        Train train;
        try {
            train = (Train) entityManager.createQuery("SELECT t FROM Train t " +
                    "WHERE t.trainNumber = :trainNumber").setParameter("trainNumber", trainNumber).getSingleResult();
        } catch (NoResultException e) {
            train = null;
        }
        return train;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Train> getAllTrains() {
        System.out.println(entityManager.createQuery("select t from Train t").getResultList());
       return entityManager.createQuery("select t from Train t").getResultList();
    }

    @Override
    public void addTrain(Train train) {
        entityManager.persist(entityManager.merge(train));
    }

    @Override
    public void editTrain(Train train) {
        entityManager.merge(train);
    }

    @Override
    public void deleteTrain(Train train) {
        entityManager.remove(entityManager.merge(train));
    }

}
