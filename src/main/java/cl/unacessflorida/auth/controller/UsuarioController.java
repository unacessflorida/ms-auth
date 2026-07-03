package cl.unacessflorida.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import cl.unacessflorida.auth.dto.UsuarioDTO;
import cl.unacessflorida.auth.model.Usuario;
import cl.unacessflorida.auth.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestion de autenticacion y usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un nuevo usuario (medico, admin, etc)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado",
            content = @Content(examples = @ExampleObject(value = "{\"username\":\"dr.perez\",\"password\":\"clave123\",\"rol\":\"medico\"}"))),
        @ApiResponse(responseCode = "400", description = "Datos invalidos"),
        @ApiResponse(responseCode = "500", description = "El usuario ya existe")
    })
    @PostMapping
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody UsuarioDTO usuario) {
        Usuario nuevoUsuario = service.registrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @Operation(summary = "Buscar un usuario por su username")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(@PathVariable String username) {
        return service.buscarPorUsername(username)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}