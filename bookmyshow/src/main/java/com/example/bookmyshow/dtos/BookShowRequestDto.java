package com.example.bookmyshow.dtos;

import com.example.bookmyshow.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookShowRequestDto {
    private String name;
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
}
