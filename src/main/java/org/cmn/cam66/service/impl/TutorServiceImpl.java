package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.Tutor;
import org.cmn.cam66.repository.TutorRepository;
import org.cmn.cam66.service.TutorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */

@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository repository;

    public TutorServiceImpl(TutorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tutor> findAll() {
        return repository.findAll();
    }

    @Override
    public Tutor findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Tutor save(Tutor tutor) {
        return repository.save(tutor);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Tutor> findByCurp(String curpT) {
        return repository.findByCurp(curpT);
    }
}