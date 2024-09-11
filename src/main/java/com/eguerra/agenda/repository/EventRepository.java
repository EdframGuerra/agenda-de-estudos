package com.eguerra.agenda.repository;

import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByName(String name);
    List<Event> findByNameContainingIgnoreCase(String titulo);
    public List<Event>findByDate(LocalDate date);

    }
