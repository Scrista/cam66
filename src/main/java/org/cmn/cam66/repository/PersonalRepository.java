package org.cmn.cam66.repository;

import org.cmn.cam66.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    Optional<Personal> findByUsuario(String username);


}
