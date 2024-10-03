package com.eguerra.agenda.repository;

import com.eguerra.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository <Agenda,Long>{
    Optional<Agenda> findByTituloContainingIgnoreCase(String user);
}
