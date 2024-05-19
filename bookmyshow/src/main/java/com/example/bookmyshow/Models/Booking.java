package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @OneToMany
    private List<ShowSeat> showSeatList;
    private Double amount;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @OneToMany(mappedBy = "booking")
    private List<Payment> payment;
    @ManyToOne
    private User user;
}
