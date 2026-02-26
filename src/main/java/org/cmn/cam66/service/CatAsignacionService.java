package org.cmn.cam66.service;

import org.cmn.cam66.entity.CatAsignacion;

import java.util.List;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface CatAsignacionService {

    List<CatAsignacion> findAll();
    CatAsignacion findById(Long id);
    CatAsignacion save(CatAsignacion catAsignacion);
    void delete(Long id);
}
