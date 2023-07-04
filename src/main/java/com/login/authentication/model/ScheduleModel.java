package com.login.authentication.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class ScheduleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    private String idUser;

    @NotEmpty
    @NotNull(message = "Id es obligario")
    @Column(nullable = false, unique = true)
    private String idSch;

    @NotEmpty
    @NotNull(message = "Nombre es obligario")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false)
    //@ValidTaskDTO
    private String numero_de_documento;

    @OneToMany(mappedBy = "scheduleModel")
    List<ActividadesModel> actividades;

    @NotEmpty
    @NotNull(message = "Fecha inicio es obligaria")
    @Column(nullable = false)
    private String fecha_inicio;

    @NotEmpty
    @NotNull(message = "Fecha fin es obligaria")
    @Column(nullable = false)
    private String fecha_fin;

    @NotEmpty
    @NotNull(message = "Total horas es obligatorio")
    @Column(nullable = false)
    private String total_horas;


    @NotEmpty
    @NotNull(message = "cliente es obligario")
    @Column(nullable = false)
    private String cliente;

    @NotEmpty
    @NotNull(message = "cliente es obligario")
    @Column(nullable = false)
    private String responsable_cliente;

    private String observaciones;
}

