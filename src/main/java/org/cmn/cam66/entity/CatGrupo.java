package org.cmn.cam66.entity;

import lombok.*;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat_grupo")
public class CatGrupo {
    @Id
    @Column(name = "idgrupo", nullable = false)
    private Integer id;

    @Column(name = "grupo", length = 50)
    private String grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idagrupador")
    private CatAsignacion idagrupador;

    public String getGrupo() {
        return this.grupo;
    }

}