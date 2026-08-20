package com.example.bff.services;

import com.example.bff.dto.RoleRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

@Service
public class RoleService {

    private final RestClient restClient;

        public RoleService(RestClient.Builder builder, @Value("${roles.url}") String rolesUrl) {

            this.restClient = builder.baseUrl(rolesUrl).build();
        }

    /**
     * GET /api/Roles
     */
    public String obtenerRoles() {

        return restClient.get().uri("/api/Roles").retrieve().body(String.class);
    }

    /**
     * GET /api/Roles/{id}
     *
     * Conservamos el código HTTP de la Function.
     */
    public ResponseEntity<String> obtenerRolPorId(Long id) {

        return restClient.get().uri("/api/Roles/{id}", id).exchange((request, response) -> {

            String body = new String(response.getBody().readAllBytes());

            return ResponseEntity.status(response.getStatusCode()).body(body);
        });
    }

    /**
     * POST /api/Roles
     */
    public String crearRol(RoleRequest rol) {

        String json = """
                {
                    "nombreRol": "%s",
                    "estado": "%s"
                }
                """.formatted(rol.getNombreRol(), rol.getEstado());

        System.out.println("===== JSON ENVIADO A FUNCTION ROLES =====");
        System.out.println(json);

        return restClient.post().uri("/api/Roles").contentType(MediaType.APPLICATION_JSON)
                .body(json).retrieve().body(String.class);
    }

    /**
     * PUT /api/Roles/{id}
     */
    public String actualizarRol(Long id, RoleRequest rol) {

        String json = """
                {
                    "nombreRol": "%s",
                    "estado": "%s"
                }
                """.formatted(rol.getNombreRol(), rol.getEstado());

        System.out.println("===== JSON ENVIADO A FUNCTION ROLES =====");
        System.out.println(json);

        return restClient.put().uri("/api/Roles/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .body(json).retrieve().body(String.class);
    }

    /**
     * DELETE /api/Roles/{id}
     */
    public void eliminarRol(Long id) {

        restClient.delete().uri("/api/Roles/{id}", id).retrieve().toBodilessEntity();
    }
}
