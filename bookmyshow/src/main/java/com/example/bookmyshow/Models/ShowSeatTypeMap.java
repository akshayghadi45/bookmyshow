package com.example.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatTypeMap extends BaseModel {
    @ManyToOne
    private Show_ show;
    @ManyToOne
    private SeatType seatType;
    private Double price;
}
