package com.eguerra.agenda.dtos;

import com.eguerra.agenda.model.Agenda;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventDto {

    private String titulo;
    private  String descricao;
    private LocalDate date;
    private LocalTime hora;
    private Agenda agenda;
}
