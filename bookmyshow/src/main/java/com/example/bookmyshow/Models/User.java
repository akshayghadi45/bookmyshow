package com.example.bookmyshow.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User  extends BaseModel{
    private String name;
    private Long phoneNumber;
    private String email;
    private String password;
}
