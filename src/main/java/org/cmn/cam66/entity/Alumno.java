package org.cmn.cam66.entity;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "alumno_seq")
    @SequenceGenerator(name = "alumno_seq", sequenceName = "alumno_seq", allocationSize = 1)
    private Integer idalumno;

    @Column(name = "paterno", length = 100)
    private String paterno;

    @Column(name = "materno", length = 100)
    private String materno;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rgrado")
    private CatGrupo rgrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "igrado")
    private CatGrupo igrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idresponsable")
    private Tutor idresponsable;

    @Column(name = "curp")
    private String curp;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fechanacimiento")
    private LocalDate fechanacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condicion")
    private CatCondicion idcondicion;



}