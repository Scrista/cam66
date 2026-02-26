package org.cmn.cam66.service;

import org.cmn.cam66.entity.CatCondicion;

import java.util.List;

/**
 * @author Santiago Cristales
 * @date 01/12/2025
 * @project cam66
 */
public interface CatCondicionService {
    List<CatCondicion> findAll();
    CatCondicion findById(Long id);
    CatCondicion save(CatCondicion CatCondicion);
    void delete(Long id);
}
