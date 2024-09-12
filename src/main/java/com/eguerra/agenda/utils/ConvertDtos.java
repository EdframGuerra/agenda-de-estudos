package com.eguerra.agenda.utils;

import com.eguerra.agenda.dtos.AgendaDto;
import com.eguerra.agenda.dtos.EventDto;
import com.eguerra.agenda.dtos.UserDto;
import com.eguerra.agenda.model.Agenda;
import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.model.User;

public class ConvertDtos {

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setName(userDto.getName());

        return user;
    }


    public static Event toEntity(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        Event event = new Event();
        event.setTitulo(eventDto.getTitulo());
        event.setDescricao(eventDto.getDescricao());
        event.setDate(eventDto.getDate());
        event.setHora(eventDto.getHora());
        event.setAgenda(eventDto.getAgenda());

        return event;
    }


    public static Agenda toEntity(AgendaDto agendaDto) {
        if (agendaDto == null) {
            return null;
        }
        Agenda agenda = new Agenda();
        agenda.setUser(agendaDto.getUser());
        agenda.setEvents(agendaDto.getEvents());

        return agenda;
    }


}
