package com.eguerra.agenda.dtos;

import com.eguerra.agenda.model.Event;
import com.eguerra.agenda.model.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AgendaDto {

    private List<Event> events;
    private User user;
}
