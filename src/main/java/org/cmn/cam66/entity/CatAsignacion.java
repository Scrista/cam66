package org.cmn.cam66.entity;

import lombok.*;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat_asignacion")
public class CatAsignacion {
    @Id
    @Column(name = "idagrupador", nullable = false)
    private Integer id;

    @Column(name = "agrupador", length = 50)
    private String agrupador;

}