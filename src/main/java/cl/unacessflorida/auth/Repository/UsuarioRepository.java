package cl.unacessflorida.auth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.unacessflorida.auth.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring Boot crea la consulta automáticamente con el nombre del método
    Optional<Usuario> findByUsername(String username);
}