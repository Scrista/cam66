package org.cmn.cam66.service;

import org.cmn.cam66.entity.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface AlumnoService {

    List<Alumno> findAll();
    Alumno findById(Long id);
    Alumno save(Alumno alumno);
    void delete(Long id);

    Optional<Alumno> findByCurp(String curp);

    Page<Alumno> findAll(Pageable sortedPageable);
}
