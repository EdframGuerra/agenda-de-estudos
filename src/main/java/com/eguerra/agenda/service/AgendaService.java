package com.eguerra.agenda.service;


import com.eguerra.agenda.dtos.AgendaDto;
import com.eguerra.agenda.model.Agenda;
import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.repository.AgendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.eguerra.agenda.utils.ConvertDtos.toEntity;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository){
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda(AgendaDto agendaDto){
        Agenda agenda = toEntity(agendaDto);
        return agendaRepository.save(agenda);
    }

    public List<Agenda>getAllAgenda(){
        return agendaRepository.findAll();
    }

    public Agenda getAgendaById(Long id){
        Optional<Agenda>agendaOptional=agendaRepository.findById(id);
        if(agendaOptional.isEmpty()){
            throw new EntityNotFoundException("Agenda not found");
        }
        return agendaOptional.get();
    }

    public Agenda getAgendaByUser(String user) {

        Optional<Agenda> agendaOptional = agendaRepository.findByTituloContainingIgnoreCase(user);
        if (agendaOptional.isEmpty()) {
            throw new NullPointerException("Agenda not found");
        }
        return agendaOptional.get();
    }

    public Optional<Agenda> deleteAgenda(Long id){
        Optional<Agenda>optionalAgenda=agendaRepository.findById(id);
        if(optionalAgenda.isPresent()){
            agendaRepository.delete(optionalAgenda.get());
        }
        else{
            throw new EntityNotFoundException("Agenda not found");
        }
        return optionalAgenda;
    }


}
