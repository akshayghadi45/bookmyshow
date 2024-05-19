package com.example.bookmyshow.services;

import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.exceptions.InvalidShowError;
import com.example.bookmyshow.exceptions.SeatUnavailableException;
import com.example.bookmyshow.exceptions.UserNotPresentException;
import com.example.bookmyshow.repositories.BookingRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public Booking BookShow(Long userId, Long showId, List<Long> showSeatIds) throws UserNotPresentException, InvalidShowError, SeatUnavailableException {
        //Get Show from show Id
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotPresentException("User not present");
        }
        User user = optionalUser.get();

        //Get User from user Id
        Optional<Show_> optionalShow_ = showRepository.findById(showId);
        if(optionalShow_.isEmpty()){
            throw  new InvalidShowError("Show selected is invalid");
        }
        Show_ show = optionalShow_.get();


        //Start transaction with isolation level serializable
        List<ShowSeat> reservedSeats = reserveSeats(showSeatIds,showId);
            //Get the seats from showSeatIds
            //Check availability
            //if !AVAILABLE  or (LOCKED && last lock is <10min)
                //throw seat not available exception
            //else
                //Update locked status
                //update locked time
        //End transaction

        //Create a booking
        Booking booking = createBooking(reservedSeats,user,show);
        return null;
    }

    private Booking createBooking(List<ShowSeat> reservedSeats, User user, Show_ show) {
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(calculateBookingAmount(reservedSeats,show));//create a price calculator method
        booking.setUser(user);
        booking.setShowSeatList(reservedSeats);
        booking.setPayment(new ArrayList<>());
        return bookingRepository.save(booking);
    }

    private Double calculateBookingAmount(List<ShowSeat> reservedSeats, Show_ show) {
        //logic to calculate pricing
        return 0.0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveSeats(List<Long> showSeatIds,Long showId) throws SeatUnavailableException {
        //Start transaction with isolation level serializable
        //Get all the seats from showSeatIds
        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);
        //Check availability
        for( ShowSeat showSeat: showSeatList){
            checkShowSeatAvailability(showSeat);
        }
        //Lock the seats for booking

        List<ShowSeat> reservedSeats = new ArrayList<ShowSeat>();
        for(ShowSeat showSeat: showSeatList){
            if(!showSeat.getShow().getId().equals(showId)){
                continue;
            }
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            reservedSeats.add(showSeat);
        }


        //if !AVAILABLE  or (LOCKED && last lock is <10min)
        //throw seat not available exception
        //else
        //Update locked status
        //update locked time
        //End transaction
        return reservedSeats;
    }

    private void checkShowSeatAvailability(ShowSeat showSeat) throws SeatUnavailableException {
        if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.UNOPERATIOINAL)){
            throw new SeatUnavailableException("This seat is broken");
        }
        if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)){
            throw new SeatUnavailableException("This seat is already booked");
        }
        if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED)){
            Long lockedDuration = Duration.between(new Date().toInstant(),showSeat.getLockedAt().toInstant()).toMinutes();
            if(lockedDuration<10){
                throw  new SeatUnavailableException("This seat is being booked by other user");
            }

        }
    }
}
