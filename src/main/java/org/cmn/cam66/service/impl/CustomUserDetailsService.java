package org.cmn.cam66.service.impl;

import org.cmn.cam66.entity.Personal;
import org.cmn.cam66.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Santiago Cristales
 * @date 25/11/2025
 * @project cam66
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonalRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Personal usuario = repo.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String[] roles = usuario.getRoles()
                .stream()
                .map(r -> r.getRol())
                .toArray(String[]::new);
        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getPassword())
                .disabled(!usuario.isActivo())
                .roles(roles) // o cargar desde BD
                .build();
    }
}