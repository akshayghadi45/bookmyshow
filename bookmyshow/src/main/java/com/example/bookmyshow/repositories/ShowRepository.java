package com.example.bookmyshow.repositories;

import com.example.bookmyshow.Models.Show_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show_,Long> {

}
