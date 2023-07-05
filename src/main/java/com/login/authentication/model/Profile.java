package com.login.authentication.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @NotNull(message = "Tipo de documento es obligatorio")
    @Column(nullable = false)
    //@ValidTaskDTO
    private String tipo_de_documento;

    @NotEmpty
    @NotNull(message = "Cargo es obligatorio")
    @Column(nullable = false)
    private String cargo;

    @NotEmpty
    @NotNull(message = "País es obligatorio")
    @Column(nullable = false)
    private String pais;

    @NotEmpty
    @NotNull(message = "Cliente es obligatorio")
    @Column(nullable = false)
    private String cliente;

    private String cliente_final;

    private String direccion;

    private String telefono;

    @Column(length = 200000)
    private String foto;
}
