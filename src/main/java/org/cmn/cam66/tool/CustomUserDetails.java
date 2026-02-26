package org.cmn.cam66.tool;


import org.cmn.cam66.entity.Personal;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final Personal usuario;

    public CustomUserDetails(Personal usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getUsuario(), usuario.getPassword(), authorities);
        this.usuario = usuario;
    } 

    public String getNombreCompleto() {
        return usuario.getNombre();
    }

    public Personal getUsuario() {
        return usuario;
    }
    
}
