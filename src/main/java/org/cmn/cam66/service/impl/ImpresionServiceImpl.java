package org.cmn.cam66.service.impl;

import org.cmn.cam66.Bitacoraimpresion;
import org.cmn.cam66.repository.BitacoraImpresionRepository;
import org.cmn.cam66.service.ImpresionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpresionServiceImpl implements ImpresionService {
    @Autowired
    BitacoraImpresionRepository bitacoraImpresionRepository;

    @Override
    public Bitacoraimpresion save(Bitacoraimpresion bitacora) {
        return bitacoraImpresionRepository.save(bitacora);
    }
}
