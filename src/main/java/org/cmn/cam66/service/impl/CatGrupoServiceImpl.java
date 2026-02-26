package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.CatGrupo;
import org.cmn.cam66.repository.CatGrupoRepository;
import org.cmn.cam66.service.CatGrupoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
@Service
public class CatGrupoServiceImpl implements CatGrupoService {

    private final CatGrupoRepository repository;

    public CatGrupoServiceImpl(CatGrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CatGrupo> findAll() {
        return repository.findAll();
    }

    @Override
    public CatGrupo findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public Optional<CatGrupo> findByGrupo(String grupo) {
        return repository.findByGrupo(grupo);
    }

    @Override
    public CatGrupo save(CatGrupo catGrupo) {
        return repository.save(catGrupo);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CatGrupo> findAllByOrderByIdAsc() {
       return  repository.findAllByOrderByIdAsc();
    }
}
