package com.eguerra.agenda.repository;

import com.eguerra.agenda.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitulo(String titulo);
    List<Event> findByTituloContainingIgnoreCase(String titulo);
    public List<Event>findByDate(LocalDate date);

    }
