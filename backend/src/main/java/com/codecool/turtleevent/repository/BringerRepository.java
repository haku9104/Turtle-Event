package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Bringer;
import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BringerRepository extends JpaRepository<Bringer, Long> {

    List<Bringer> findAllByUser(User user);
    List<Bringer> findAllByToBring(ToBring toBring);

}