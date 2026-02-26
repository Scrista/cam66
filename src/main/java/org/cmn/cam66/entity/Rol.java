package org.cmn.cam66.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @Column(name = "idrol", nullable = false)
    private Integer id;

    @Column(name = "rol", length = 50)
    private String rol;

}