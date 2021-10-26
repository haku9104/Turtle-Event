package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Bringer;
import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.ToBring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToBringRepository extends JpaRepository<ToBring, Long> {

    List<ToBring> findAllByEvent(Event event);

}