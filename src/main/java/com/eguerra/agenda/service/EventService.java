package com.eguerra.agenda.service;

import com.eguerra.agenda.dtos.EventDto;
import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.eguerra.agenda.utils.ConvertDtos.toEntity;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventDto eventDto){
        Event event = toEntity(eventDto);
        return eventRepository.save(event);
    }

    public List<Event>getAllEvent(){
        return eventRepository.findAll();
    }

    public Event getEventById(Long id){
        Optional<Event>eventOptional = eventRepository.findById(id);
        if(eventOptional.isEmpty()){
            throw new EntityNotFoundException("Event not fond");
        }

        return eventOptional.get();
    }

    public List<Event>findByTitulo(String titulo){
        List<Event>eventExact = eventRepository.findByTitulo(titulo);
        return !eventExact.isEmpty()?eventExact:eventRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Event>findByDate(LocalDate date){
        return eventRepository.findByDate(date);
    }

    public Event updateEvent(Long id, EventDto eventDto){
        Optional<Event>optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isEmpty()){
            throw new EntityNotFoundException("Event not found");
        }

        Event event = optionalEvent.get();

        event.setTitulo(eventDto.getTitulo());
        event.setDescricao(eventDto.getDescricao());
        event.setDate(eventDto.getDate());
        event.setHora(eventDto.getHora());
        event.setAgenda(eventDto.getAgenda());

        return  eventRepository.save(event);
    }

    public Optional<Event> deleteEvent(Long id){
        Optional<Event> optionalEvent=eventRepository.findById(id);
        if(optionalEvent.isPresent()){
            eventRepository.delete(optionalEvent.get());
        }
        else{
            throw new EntityNotFoundException("Event not found");
        }
        return optionalEvent;
    }




}
