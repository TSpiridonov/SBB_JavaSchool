package ru.javaschool.sbb.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "trip", schema = "sbb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_station_id")
    private Station arrivalStation;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "canceled")
    private boolean canceled;

    public Trip(Train train, Station departureStation, Station arrivalStation, Timestamp departureTime,
                Timestamp arrivalTime) {
        this.train = train;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
