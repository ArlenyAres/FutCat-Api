package arlenyAres.FutCat_Api.security;

import java.util.Collection;

import arlenyAres.FutCat_Api.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;



@SuppressWarnings("serial")
public class UsuarioSecurity implements UserDetails {

    private final Usuario usuario;

    public UsuarioSecurity(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public String getPassword() {
        return usuario.getContraseña();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Por ahora null o Collections.emptyList().
        return null;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}