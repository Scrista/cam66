package org.cmn.cam66.entity;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "personal")
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idpersonal", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "curp", length = 50)
    private String curp;

    @Column(name = "rfc", length = 20)
    private String rfc;

    @Column(name = "plaza", length = 50)
    private String plaza;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idasigncion")
    private CatAsignacion idasigncion;

    @Column(name = "estudios", length = 100)
    private String estudios;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @Column(name = "funcion", length = 100)
    private String funcion;

    @Column(name = "horario", length = 150)
    private String horario;

    @Column(name = "ingresosep")
    private LocalDate ingresosep;

    @Column(name = "ingresonivel")
    private LocalDate ingresonivel;

    @Column(name = "ingresoct")
    private LocalDate ingresoct;

    @Column(name = "domicilio", length = 500)
    private String domicilio;

    @Column(name = "clavect", length = 15)
    private String clavect;

    @Column(name = "ctcobro", length = 15)
    private String ctcobro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rusuario_rol",
            joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol")
    )
    private List<Rol> roles;


    @OneToMany
    private List<Resguardos> resguardos;

    @Column(name = "usuario", length = 50)
    private String usuario;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "activo")
    private boolean activo;
}