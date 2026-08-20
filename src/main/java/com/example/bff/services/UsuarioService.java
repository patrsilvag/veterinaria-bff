package com.example.bff.services;

import com.example.bff.dto.UsuarioRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class UsuarioService {

    private final RestClient restClient;

    public UsuarioService(RestClient.Builder builder) {

        this.restClient = builder.baseUrl("http://localhost:7071").build();
    }

    /**
     * GET /api/Usuarios
     */
    public String obtenerUsuarios() {

        return restClient.get().uri("/api/Usuarios").retrieve().body(String.class);
    }

    /**
     * GET /api/Usuarios/{id}
     */
    public ResponseEntity<String> obtenerUsuarioPorId(Long id) {

    return restClient
            .get()
            .uri("/api/Usuarios/{id}", id)
            .exchange((request, response) -> {

                String body = new String(
                        response.getBody().readAllBytes());

                return ResponseEntity
                        .status(response.getStatusCode())
                        .body(body);
            });
}
    /**
     * POST /api/Usuarios
     */
    public String crearUsuario(UsuarioRequest usuario) {

        String json = """
                {
                    "idRol": %d,
                    "nombreUsuario": "%s",
                    "email": "%s",
                    "estado": "%s"
                }
                """.formatted(usuario.getIdRol(), usuario.getNombreUsuario(), usuario.getEmail(),
                usuario.getEstado());

        System.out.println("===== JSON ENVIADO A FUNCTION =====");
        System.out.println(json);

        return restClient.post().uri("/api/Usuarios").contentType(MediaType.APPLICATION_JSON)
                .body(json).retrieve().body(String.class);
    }

    /**
     * PUT /api/Usuarios/{id}
     */
    public String actualizarUsuario(Long id, UsuarioRequest usuario) {

        String json = """
                {
                    "idRol": %d,
                    "nombreUsuario": "%s",
                    "email": "%s",
                    "estado": "%s"
                }
                """.formatted(usuario.getIdRol(), usuario.getNombreUsuario(), usuario.getEmail(),
                usuario.getEstado());

        return restClient.put().uri("/api/Usuarios/{id}", id)
                .contentType(MediaType.APPLICATION_JSON).body(json).retrieve().body(String.class);
    }

    /**
     * DELETE /api/Usuarios/{id}
     */
    public void eliminarUsuario(Long id) {

        restClient.delete().uri("/api/Usuarios/{id}", id).retrieve().toBodilessEntity();
    }
}
