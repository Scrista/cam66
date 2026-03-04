package org.cmn.cam66.entity;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */

@Entity(name="material")
public class MaterialDidactico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "articulo")
    private String articulo;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "piezas")
    private Integer piezas;

    @Column(name = "estado")
    private String estado;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "cantidadRestante")
    private Integer cantidadRestante;

    @Column(name = "sididactico")
    private Boolean sididactico;

    public Boolean getSididactico() {
        return sididactico;
    }

    public void setSididactico(Boolean sididactico) {
        this.sididactico = sididactico;
    }

    public Integer getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(Integer cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
