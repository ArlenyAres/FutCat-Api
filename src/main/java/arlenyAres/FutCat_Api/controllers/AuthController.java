package arlenyAres.FutCat_Api.controllers;

import arlenyAres.FutCat_Api.models.Usuario;
import arlenyAres.FutCat_Api.repositories.UsuarioRepository;
import arlenyAres.FutCat_Api.security.jwt.JwtUtils;
import arlenyAres.FutCat_Api.services.SecurityUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final SecurityUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, SecurityUserDetailsService userDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword());
        try {
            // Intentar autenticar (llama a loadUserByUsername y compara password)
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Si ok, cargamos los detalles del usuario
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // Generar token
        String token = jwtUtils.generarToken(userDetails.getUsername());

        // Retornar token en JSON
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Encriptar la contraseña
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        // Crear un nuevo usuario
        Usuario newUser = new Usuario(
                request.getNombre(),
                request.getApellidos(),
                request.getCorreo(),
                request.getUsername(),
                encryptedPassword
        );

        // Guardar el usuario en la base de datos
        usuarioRepository.save(newUser);

        return ResponseEntity.ok("Usuario registrado");
    }

    // Clases de petición/respuesta
    @Setter
    @Getter
    static class LoginRequest {
        private String username;
        private String password;

    }

    @Getter
    static class JwtResponse {
        private String token;
        public JwtResponse(String token) { this.token = token; }
    }

    @Getter
    @Setter
    static class RegisterRequest {
        private String nombre;
        private String apellidos;
        private String correo;
        private String username;
        private String password;

    }
}