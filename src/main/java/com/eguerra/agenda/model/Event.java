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

    @Column(name = "Tb_titulo", nullable = false, length = 500)
    private String titulo;

    @Column(name = "Tb_descricao", nullable = false, length = 3500)
    private String descricao;

    @Column(name = "Tb_data", nullable = false)
    private LocalDate date;

    @Column(name = "Tb_hora", nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "agenda_id") // Nome da coluna que fará a referência
    private Agenda agenda;
}
