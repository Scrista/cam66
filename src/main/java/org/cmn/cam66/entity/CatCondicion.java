package org.cmn.cam66.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat_condicion")
public class CatCondicion {
    @Id
    @Column(name = "idcondicion", nullable = false)
    private Integer id;

    @Column(name = "clave", length = 10)
    private String clave;

    @Column(name = "condicion", length = 60)
    private String condicion;

}