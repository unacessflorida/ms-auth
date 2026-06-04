package cl.unacessflorida.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.unacessflorida.auth.dto.UsuarioDTO;
import cl.unacessflorida.auth.model.Usuario;
import cl.unacessflorida.auth.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody UsuarioDTO usuario) {
        Usuario nuevoUsuario = service.registrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}