package org.cmn.cam66.service;

import org.cmn.cam66.entity.MaterialDidactico;
import org.cmn.cam66.entity.Resguardos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */
public interface AdministracionService {
    Page<MaterialDidactico> getMaterial(Pageable sortedPageable);

    List<MaterialDidactico> getAllArticulo();

    List<Resguardos> getResguardos();
}
