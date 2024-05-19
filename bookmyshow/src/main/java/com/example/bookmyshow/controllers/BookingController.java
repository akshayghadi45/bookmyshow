package com.example.bookmyshow.controllers;

import com.example.bookmyshow.Models.Booking;
import com.example.bookmyshow.dtos.BookShowRequestDto;
import com.example.bookmyshow.dtos.BookShowResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.exceptions.InvalidShowError;
import com.example.bookmyshow.exceptions.SeatUnavailableException;
import com.example.bookmyshow.exceptions.UserNotPresentException;
import com.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookinService;
    public BookShowResponseDto bookShow(BookShowRequestDto requestDto){
        try {
            Booking booking = bookinService.BookShow(requestDto.getUserId(), requestDto.getShowId(), requestDto.getShowSeatIds());
            return new BookShowResponseDto(booking.getAmount(), ResponseStatus.SUCCESS, booking.getId(), "");
        }catch (UserNotPresentException userNotPresentException){
            System.out.println(userNotPresentException.getMessage());
            return  new BookShowResponseDto(null, ResponseStatus.FAILURE,null,userNotPresentException.getMessage());
        }
        catch (InvalidShowError invalidShowError){
            System.out.println(invalidShowError.getMessage());
            return  new BookShowResponseDto(null, ResponseStatus.FAILURE,null,invalidShowError.getMessage());
        }
        catch (SeatUnavailableException seatUnavailableException){
            System.out.println(seatUnavailableException.getMessage());
            return new BookShowResponseDto(null, ResponseStatus.FAILURE,null,seatUnavailableException.getMessage());
        }
        catch (Exception e){
            return  new BookShowResponseDto(null, ResponseStatus.FAILURE,null,"Internal Server Error");
        }
        //return null;
    }
}
