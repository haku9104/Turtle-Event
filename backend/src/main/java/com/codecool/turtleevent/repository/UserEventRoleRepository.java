package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.UserEventRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRoleRepository extends JpaRepository<UserEventRole, Long> {

    List<UserEventRole> findAllByEvent(Event event);

    List<UserEventRole> findAllByUser(User user);
}