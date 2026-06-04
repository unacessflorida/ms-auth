package cl.unacessflorida.auth.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data 
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password; // Se guardará plano para máxima velocidad
    private String rol;      // ENFERMERA o MATRONA
}