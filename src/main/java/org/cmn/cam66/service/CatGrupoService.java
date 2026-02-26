package org.cmn.cam66.service;

import org.cmn.cam66.entity.CatGrupo;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface CatGrupoService {
    List<CatGrupo> findAll();
    CatGrupo findById(Long id);
    Optional<CatGrupo> findByGrupo(String name);
    CatGrupo save(CatGrupo catGrupo);
    void delete(Long id);

    List<CatGrupo> findAllByOrderByIdAsc();
}
