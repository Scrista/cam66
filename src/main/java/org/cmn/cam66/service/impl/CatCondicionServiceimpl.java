package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.CatCondicion;
import org.cmn.cam66.repository.CatCondicionRepository;
import org.cmn.cam66.service.CatCondicionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santiago Cristales
 * @date 01/12/2025
 * @project cam66
 */

@Service
public class CatCondicionServiceimpl implements CatCondicionService {

    private final CatCondicionRepository  repository;

    public CatCondicionServiceimpl(CatCondicionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CatCondicion> findAll() {
        return repository.findAll();
    }

    @Override
    public CatCondicion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CatCondicion save(CatCondicion catCondicion) {
        return repository.save(catCondicion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
