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
@Table(name = "company")
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    @Column(unique = true)
    private String idClient;

    @NotEmpty
    @NotBlank(message = "Cliente no puede estar vacio")
    @NotNull
    private String client;

    private String client_final;

    @NotEmpty
    @NotNull(message = "Contrato cliente es obligario")
    @Column(nullable = false)
    private String contacto_cliente;

    private String contacto_cliente_final;

    @NotEmpty
    @NotNull(message = "Fecha contrato final es obligario")
    @Column(nullable = false)
    private String fecha_contrato;
}
