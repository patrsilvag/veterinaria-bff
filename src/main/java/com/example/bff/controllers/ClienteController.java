package com.example.bff.controllers;

import com.example.bff.dto.ClienteRequest;
import com.example.bff.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * GET /api/clientes
     */
    @GetMapping
    public ResponseEntity<String> obtenerClientes() {

        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    /**
     * GET /api/clientes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerClientePorId(@PathVariable Long id) {

        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    /**
     * POST /api/clientes
     */
    @PostMapping
    public ResponseEntity<String> crearCliente(@RequestBody ClienteRequest cliente) {

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(cliente));
    }

    /**
     * PUT /api/clientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id,
            @RequestBody ClienteRequest cliente) {

        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    /**
     * DELETE /api/clientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}
