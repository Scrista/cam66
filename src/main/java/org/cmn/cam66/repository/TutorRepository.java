package org.cmn.cam66.repository;

import org.cmn.cam66.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT c FROM Tutor c WHERE c.curp = :curp")
    Optional<Tutor> findByCurp(@Param("curp") String curp);
}
