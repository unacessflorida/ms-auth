package cl.unacessflorida.auth.service;

import org.springframework.stereotype.Service;

import cl.unacessflorida.auth.Repository.UsuarioRepository;
import cl.unacessflorida.auth.dto.UsuarioDTO;
import cl.unacessflorida.auth.model.Usuario;

@Service
public class UsuarioService {
  
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario registrar(UsuarioDTO dto) {
       
        if (repo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

       
        Usuario u = new Usuario();
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword()); 
        u.setRol(dto.getRol());

        return repo.save(u);
    }
}