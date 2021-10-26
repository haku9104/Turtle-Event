package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.UserEventRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
}