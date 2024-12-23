package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.repositories.UsuarioRepository;
import arlenyAres.FutCat_Api.security.UsuarioSecurity;
import arlenyAres.FutCat_Api.models.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public SecurityUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = this.usuarioRepository.findByUsername(username);
        if (optUsuario.isPresent()) {
            return new UsuarioSecurity(optUsuario.get());
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}