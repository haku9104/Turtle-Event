package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.Doer;
import com.codecool.turtleevent.model.ToDo;
import com.codecool.turtleevent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoerRepository extends JpaRepository<Doer, Long> {

    List<Doer> findAllByToDo(ToDo toDo);

    List<Doer> findAllByUser(User user);
}