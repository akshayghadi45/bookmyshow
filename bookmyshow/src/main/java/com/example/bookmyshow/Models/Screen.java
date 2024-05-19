package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private Integer screenNumber;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Format> formatList;
    @OneToMany(mappedBy = "screen")
    private List<Seat> Seats;
    //private List<Show> shows;
}
