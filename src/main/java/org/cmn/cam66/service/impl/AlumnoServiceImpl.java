package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.Alumno;
import org.cmn.cam66.repository.AlumnoRepository;
import org.cmn.cam66.service.AlumnoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository repository;

    public AlumnoServiceImpl(AlumnoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Alumno> findAll() {
        return repository.findAll();
    }

    @Override
    public Alumno findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return repository.save(alumno);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Alumno> findByCurp(String curp) {
        return repository.findByCurp(curp);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Alumno> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}