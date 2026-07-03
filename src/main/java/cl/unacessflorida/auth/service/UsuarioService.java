package cl.unacessflorida.auth.service;

import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.unacessflorida.auth.dto.UsuarioDTO;
import cl.unacessflorida.auth.model.Usuario;
import cl.unacessflorida.auth.repository.UsuarioRepository;

@Service
public class UsuarioService {
  
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario registrar(UsuarioDTO dto) {
        log.info("Iniciando registro de usuario: {}", dto.getUsername());
       
        if (repo.findByUsername(dto.getUsername()).isPresent()) {
            log.warn("Intento de registro fallido: El usuario {} ya existe", dto.getUsername());
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Usuario u = new Usuario();
        u.setUsername(dto.getUsername());
        
        String passwordHasheada = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        u.setPassword(passwordHasheada); 
        u.setRol(dto.getRol().toUpperCase());

        Usuario usuarioGuardado = repo.save(u);
        log.info("Usuario {} registrado exitosamente con ID {}", usuarioGuardado.getUsername(), usuarioGuardado.getId());
        
        return usuarioGuardado;
    }
}