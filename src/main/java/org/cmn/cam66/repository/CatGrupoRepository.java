package org.cmn.cam66.repository;

import org.cmn.cam66.entity.CatGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface CatGrupoRepository extends JpaRepository<CatGrupo, Long> {

    @Query("SELECT c FROM CatGrupo c WHERE c.grupo = :grupo")
    Optional<CatGrupo> findByGrupo(@Param("grupo") String grupo);

    List<CatGrupo> findAllByOrderByIdAsc();
}