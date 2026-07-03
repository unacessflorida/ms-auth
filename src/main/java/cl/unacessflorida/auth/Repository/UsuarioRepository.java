package cl.unacessflorida.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.unacessflorida.auth.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);
}