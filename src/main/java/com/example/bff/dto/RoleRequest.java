package com.example.bff.dto;

public class RoleRequest {

    private String nombreRol;
    private String estado;

    public RoleRequest() {}

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
