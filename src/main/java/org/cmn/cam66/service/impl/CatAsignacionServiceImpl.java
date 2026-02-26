package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.CatAsignacion;
import org.cmn.cam66.repository.CatAsignacionRepository;
import org.cmn.cam66.service.CatAsignacionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
@Service
public class CatAsignacionServiceImpl implements CatAsignacionService {

    private final CatAsignacionRepository repository;

    public CatAsignacionServiceImpl(CatAsignacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CatAsignacion> findAll() {
        return repository.findAll();
    }

    @Override
    public CatAsignacion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CatAsignacion save(CatAsignacion catAsignacion) {
        return repository.save(catAsignacion);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}