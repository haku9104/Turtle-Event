package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.Message;
import com.codecool.turtleevent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByEvent(Event event);

    List<Message> findAllByAuthor(User user);
}