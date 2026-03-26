package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.Personal;
import org.cmn.cam66.repository.PersonalRepository;
import org.cmn.cam66.service.PersonalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Cristales
 * @date 23/11/2025
 * @project cam66
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository repository;

    public PersonalServiceImpl(PersonalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Personal> findAll() {
        return repository.findAll();
    }

    @Override
    public Personal findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Personal save(Personal personal) {
        return repository.save(personal);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Personal> findByUsuario(String username) {
        return repository.findByUsuario(username);
    }
}