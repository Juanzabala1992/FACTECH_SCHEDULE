package com.login.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "follow")
public class FollowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followId", referencedColumnName = "followId")
    @JsonIgnore
    private FollowModelExpand followModel;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    private String idUser;

    @NotEmpty
    @NotNull(message = "Nombre es obligario")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @NotNull(message = "Apellido es obligario")
    @Column(nullable = false)
    private String apellido;

    @NotEmpty
    @NotNull(message = "Cargo es obligatorio")
    @Column(nullable = false)
    private String cargo;

    @NotEmpty
    @NotNull(message = "Cliente es obligatorio")
    @Column(nullable = false)
    private String cliente;

    private String finalClient;

    @NotEmpty
    @NotBlank(message = "Contacto cliente no puede estar vacio")
    @NotNull
    private String clientContact;

    @NotEmpty
    @NotBlank(message = "Fecha contrato no puede estar vacio")
    @NotNull
    private String dateContract;

    private String contractFinalClient;

    @NotEmpty
    @NotBlank(message = "Seguimiento contrato no puede estar vacio")
    @NotNull
    private String follow;

    @NotEmpty
    @NotBlank(message = "Punto de atencion contrato no puede estar vacio")
    @NotNull
    private String atention;

}
