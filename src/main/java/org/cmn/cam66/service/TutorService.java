package org.cmn.cam66.service;

import org.cmn.cam66.entity.Tutor;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface TutorService {

    List<Tutor> findAll();
    Tutor findById(Long id);
    Tutor save(Tutor tutor);
    void delete(Long id);

    Optional<Tutor> findByCurp(String curpT);
}
