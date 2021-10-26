package com.codecool.turtleevent.repository;

import com.codecool.turtleevent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}