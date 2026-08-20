package com.example.bff.controllers;

import com.example.bff.dto.UsuarioRequest;
import com.example.bff.services.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * GET /api/usuarios
     */
    @GetMapping
    public ResponseEntity<String> obtenerUsuarios() {

        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    /**
     * GET /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerUsuarioPorId(@PathVariable Long id) {

        return usuarioService.obtenerUsuarioPorId(id);
    }

    /**
     * POST /api/usuarios
     */
    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioRequest usuario) {

        System.out.println("===== POST /api/usuarios RECIBIDO EN BFF =====");
        System.out.println("idRol: " + usuario.getIdRol());
        System.out.println("nombreUsuario: " + usuario.getNombreUsuario());
        System.out.println("email: " + usuario.getEmail());
        System.out.println("estado: " + usuario.getEstado());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuario));
    }

    /**
     * PUT /api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long id,
            @RequestBody UsuarioRequest usuario) {

        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    /**
     * DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
