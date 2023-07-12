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
@Table(name = "notifications")
public class Notifications {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @NotEmpty
  @NotBlank(message = "Id no puede estar vacio")
  @NotNull
  private String idUser;

  @NotEmpty
  @NotNull(message = "Contenido es obligario")
  @Column(nullable = false)
  private String content;

  @NotEmpty
  @NotNull(message = "Destinatario es obligario")
  @Column(nullable = false)
  private String destination;

  @NotEmpty
  @NotNull(message = "Origen es obligario")
  @Column(nullable = false)
  private String origin;

  @NotEmpty
  @NotNull(message = "Estado es obligario")
  @Column(nullable = false)
  private Boolean state;
}
