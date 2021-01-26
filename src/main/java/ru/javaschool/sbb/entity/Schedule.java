package ru.javaschool.sbb.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "schedule", schema = "sbb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(name = "station_index")
    private int stationIndex;

    @ManyToOne
    @JoinColumn(name = "station_from")
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "station_to")
    private Station stationTo;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "departure_time")
    private Timestamp departureTime;


}