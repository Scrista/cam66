package org.cmn.cam66.entity;

import lombok.*;

import jakarta.persistence.*;
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tutor_seq")
    @SequenceGenerator(name = "tutor_seq", sequenceName = "tutor_seq", allocationSize = 1)
    private Integer idtutor;

    @Column(name = "paterno", length = 100)
    private String paterno;

    @Column(name = "materno", length = 100)
    private String materno;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "curp", length = 30)
    private String curp;

    @Builder
    public Tutor(Integer id, String paterno, String materno, String nombre, String curp) {
        this.idtutor = id;
        this.paterno = paterno;
        this.materno = materno;
        this.nombre = nombre;
        this.curp = curp;
    }

    protected Tutor() {}
}