package org.cmn.cam66;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cmn.cam66.entity.Personal;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bitacoraimpresion")
public class Bitacoraimpresion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbitacora", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "cobrar")
    private BigDecimal cobrar;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpersonal")
    private Personal idpersonal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcreador")
    private Personal idcreador;

    @ColumnDefault("false")
    @Column(name = "cobrado")
    private Boolean cobrado;

    @Column(name = "valor")
    private BigDecimal valor;


}