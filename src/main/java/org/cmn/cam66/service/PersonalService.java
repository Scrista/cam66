package org.cmn.cam66.service;

import org.cmn.cam66.entity.Personal;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface PersonalService {
    List<Personal> findAll();
    Personal findById(Long id);
    Personal save(Personal personal);
    void delete(Long id);

    Optional<Personal> findByUsuario(String username);
}
