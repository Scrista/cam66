package org.cmn.cam66.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */

@Entity(name = "resguardos")
public class Resguardos implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long idResguardo;

    /*@ManyToMany
    private List<Personal> personal;*/

    @ManyToMany
    private List<DetalleMaterial> materials;

    @Column(name = "fechaAlta")
    private Date fechaAlta;

    @Column(name = "fechaDevolucion")
    private Date fechaDevolucion;

    @Column( columnDefinition = "TEXT")
    private String obsAutoriza;

    @Column( columnDefinition = "TEXT")
    private String obsDevolucion;


    @ManyToOne
    @JoinColumn(name = "autoriza")
    private Personal autoriza;

    public Long getIdResguardo() {
        return idResguardo;
    }

    public void setIdResguardo(Long idResguardo) {
        this.idResguardo = idResguardo;
    }

 /*   public List<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Personal> personal) {
        this.personal = personal;
    }*/

    public List<DetalleMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<DetalleMaterial> materials) {
        this.materials = materials;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Personal getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(Personal autoriza) {
        this.autoriza = autoriza;
    }

    public Long getIdMaterialPersonal() {
        return idResguardo;
    }

    public void setIdMaterialPersonal(Long idResguardo) {
        this.idResguardo = idResguardo;
    }

}
