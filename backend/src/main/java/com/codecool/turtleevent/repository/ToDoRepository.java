package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByEvent(Event event);

}