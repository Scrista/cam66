package org.cmn.cam66.repository;

import org.cmn.cam66.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @Query("select a from Alumno a where a.curp = ?1")
    Optional<Alumno> findByCurp(String curp);
}
