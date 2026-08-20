package com.example.bff.controllers;

import com.example.bff.dto.RoleRequest;
import com.example.bff.services.RoleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * GET /api/roles
     */
    @GetMapping
    public ResponseEntity<String> obtenerRoles() {

        return ResponseEntity.ok(roleService.obtenerRoles());
    }

    /**
     * GET /api/roles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerRolPorId(@PathVariable Long id) {

        return roleService.obtenerRolPorId(id);
    }

    /**
     * POST /api/roles
     */
    @PostMapping
    public ResponseEntity<String> crearRol(@RequestBody RoleRequest rol) {

        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.crearRol(rol));
    }

    /**
     * PUT /api/roles/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRol(@PathVariable Long id,
            @RequestBody RoleRequest rol) {

        return ResponseEntity.ok(roleService.actualizarRol(id, rol));
    }

    /**
     * DELETE /api/roles/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {

        roleService.eliminarRol(id);

        return ResponseEntity.noContent().build();
    }
}
