package com.login.authentication.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    private String idUser;

    @NotEmpty
    @NotNull(message = "Email es obligario")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty
    @NotNull(message = "Nombre es obligario")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @NotNull(message = "Apellido es obligario")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false, unique = true)
    //@ValidTaskDTO
    private String numero_de_documento;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false)
    //@ValidTaskDTO
    private String tipo_de_documento;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false)
    private String cargo;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false)
    private String pais;

    private String direccion;

    private String telefono;

    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_de_documento() {
        return numero_de_documento;
    }

    public void setNumero_de_documento(String numero_de_documento) {
        this.numero_de_documento = numero_de_documento;
    }

    public String getTipo_de_documento() {
        return tipo_de_documento;
    }

    public void setTipo_de_documento(String tipo_de_documento) {
        this.tipo_de_documento = tipo_de_documento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
