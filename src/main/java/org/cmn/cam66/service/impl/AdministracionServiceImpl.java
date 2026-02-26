package org.cmn.cam66.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cmn.cam66.entity.MaterialDidactico;
import org.cmn.cam66.repository.MaterialRepository;
import org.cmn.cam66.service.AdministracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Santiago Cristales
 * @date 19/02/2026
 * @project cam66
 */
@Service
public class AdministracionServiceImpl implements AdministracionService {

    @Autowired
    MaterialRepository materialRepository;

    @Override
    public Page<MaterialDidactico> getMaterial(Pageable sortedPageable) {


        return materialRepository.getMaterialActivoAndRestante(true,0,sortedPageable);
    }

    @Override
    public   List<MaterialDidactico> getAllArticulo() {
        List<MaterialDidactico> mtr =  materialRepository.getAllMaterialActivoAndRestante(true,0);



//

        return mtr;
    }
}
