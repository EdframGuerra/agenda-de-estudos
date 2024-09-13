package com.eguerra.agenda.controllers;

import com.eguerra.agenda.dtos.EventDto;
import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Track;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class EventControllers {

    private  final EventService eventService;

    public EventControllers(EventService eventService){
        this.eventService=eventService;
    }

    @PostMapping("/event")
    public ResponseEntity<Object>saveEvent(@RequestBody @Valid EventDto eventDto){
        try{
            Event saveEvent = eventService.createEvent(eventDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Event created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating event");
        }
    }

    @GetMapping("/eventList")
    public ResponseEntity<List<Event>>listEvent(){

        try {
            List<Event>eventList = eventService.getAllEvent();
            return ResponseEntity.ok(eventList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/eventList/{id}")
    public ResponseEntity<Object>listOneEvent(@PathVariable(value = "id") Long id){
        try{
            Optional<Event>optionalEvent= Optional.ofNullable(eventService.getEventById(id));
            if(optionalEvent.isPresent()){
                return ResponseEntity.ok(optionalEvent.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An error occurred while processing the request.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/eventTitulo")
        public ResponseEntity<List<Event>>findEvent(@RequestParam String titulo){

        try {
            List<Event> eventList=eventService.findByTitulo(titulo);
            if(eventList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(eventList,HttpStatus.OK);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @GetMapping("/eventDate")
    public ResponseEntity<List<Event>>findEvent(@RequestParam LocalDate date){

        try {
            List<Event> eventList=eventService.findByDate(date);
            if(eventList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(eventList,HttpStatus.OK);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/eventHora")
    public ResponseEntity<List<Event>>findHora(@RequestParam LocalTime hora){

        try {
            List<Event> eventList=eventService.findByHora(hora);
            if(eventList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(eventList,HttpStatus.OK);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/eventUpdate/{id}")
    public ResponseEntity<Object>updateEvent(@PathVariable(value = "id")Long id, @RequestBody @Valid EventDto eventDto){

        try {
            Optional<Event>updateEvent=Optional.ofNullable(eventService.updateEvent(id,eventDto));
            return ResponseEntity.ok("Event updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to update event");
        }
    }

    @DeleteMapping("/eventDelete/{id}")
    public ResponseEntity<Object>deleteEvent(@PathVariable(value = "id")Long id){

        try {
            Optional<Event>deleteEvent=eventService.deleteEvent(id);
            return ResponseEntity.ok("Event successfully deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error when deleting event"));
        }
    }

}