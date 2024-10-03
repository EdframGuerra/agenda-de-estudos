package com.eguerra.agenda.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_eventos")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 5000)
    private String titulo;

    @Column(name = "descricao", nullable = false, length = 5000)
    private String descricao;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    public Event(String titulo, String descricao, LocalDate date, LocalTime hora, Agenda agenda) {
    }
}
