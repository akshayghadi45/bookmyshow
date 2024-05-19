package com.example.bookmyshow.dtos;


import com.example.bookmyshow.Models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookShowResponseDto {
    private Double amount;
    private ResponseStatus responseStatus;
    private Long bookingId;
    private String failureReason;


    public BookShowResponseDto(Double amount, ResponseStatus responseStatus, Long bookingId, String failureReason) {
        this.amount = amount;
        this.responseStatus = responseStatus;
        this.bookingId = bookingId;
        this.failureReason = failureReason;
    }
}
