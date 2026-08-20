package com.example.bff.services;

import com.example.bff.dto.ClienteRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ClienteService {

    private final RestClient restClient;

    public ClienteService(RestClient.Builder builder,
            @Value("${clientes.url}") String clientesUrl) {

        this.restClient = builder.baseUrl(clientesUrl).build();
    }

    /**
     * GET /api/clientes
     */
    public String obtenerClientes() {

        return restClient.get().uri("/api/clientes").retrieve().body(String.class);
    }

    /**
     * GET /api/clientes/{id}
     */
    public String obtenerClientePorId(Long id) {

        return restClient.get().uri("/api/clientes/{id}", id).retrieve().body(String.class);
    }

    /**
     * POST /api/clientes
     */
    public String crearCliente(ClienteRequest cliente) {

        return restClient.post().uri("/api/clientes").body(cliente).retrieve().body(String.class);
    }

    /**
     * PUT /api/clientes/{id}
     */
    public String actualizarCliente(Long id, ClienteRequest cliente) {

        return restClient.put().uri("/api/clientes/{id}", id).body(cliente).retrieve()
                .body(String.class);
    }

    /**
     * DELETE /api/clientes/{id}
     */
    public void eliminarCliente(Long id) {

        restClient.delete().uri("/api/clientes/{id}", id).retrieve().toBodilessEntity();
    }
}
