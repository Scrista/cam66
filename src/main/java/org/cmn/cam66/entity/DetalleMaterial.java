package org.cmn.cam66.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */

@Entity(name = "detalleMaterial")
public class DetalleMaterial  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private static final long serialVersionUID = 1L;


    @OneToMany
    private List<MaterialDidactico> materialDidacticoList= new ArrayList<MaterialDidactico>();

    @Column(name = "cantidad")
    private Integer cantidad;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
