package org.cmn.cam66.repository;

import org.cmn.cam66.entity.MaterialDidactico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */
@Repository
public interface MaterialRepository extends JpaRepository<MaterialDidactico, Long>  {


    Page<MaterialDidactico> findByActivoTrueAndCantidadRestanteGreaterThan(int i, Pageable sortedPageable);

    @Query("""
       SELECT m 
       FROM material m 
       WHERE m.activo = :activo 
       AND m.cantidadRestante > :restante
       """)
    Page<MaterialDidactico> getMaterialActivoAndRestante(
            @Param("activo") Boolean activo,
            @Param("restante") Integer restante,
            Pageable pageable);



    @Query("""
       SELECT m 
       FROM material m 
       WHERE m.activo = :activo 
       AND m.cantidadRestante > :restante
       """)
    List<MaterialDidactico> getAllMaterialActivoAndRestante(
            @Param("activo") Boolean activo,
            @Param("restante") Integer restante);




}


