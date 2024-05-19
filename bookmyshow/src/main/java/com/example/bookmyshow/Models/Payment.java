package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
        @ManyToOne
        private Booking booking;
        @Enumerated(EnumType.ORDINAL)
        private PaymentMode paymentMode;
        @Enumerated(EnumType.ORDINAL)
        private PaymentStatus paymentStatus;

}
