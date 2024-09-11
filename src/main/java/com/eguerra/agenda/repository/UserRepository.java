package com.eguerra.agenda.repository;

import com.eguerra.agenda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
    List<User> findByName(String name);
    List<User> findByNameContainingIgnoreCase(String name);
}
