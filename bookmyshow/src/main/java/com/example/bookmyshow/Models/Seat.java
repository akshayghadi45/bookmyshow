package com.example.bookmyshow.Models;

import ch.qos.logback.classic.joran.sanity.IfNestedWithinSecondPhaseElementSC;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNumber;
    @ManyToOne
    private Screen screen;
    @ManyToOne
    private SeatType seatType;
    private Integer rowNum;
    private Integer colNum;
}
