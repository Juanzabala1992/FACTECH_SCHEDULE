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
@Table(name = "follow_admin")
public class FollowModelExpand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull(message = "Id es obligatorio")
    @Column(nullable = false, unique = true)
    private String followId;

    @OneToMany(mappedBy = "followModel")
    List<FollowModel> follow;

    @NotEmpty
    @NotNull(message = "Fecha inicial es obligatoria")
    @Column(nullable = false)
    private String init_date;

    @NotEmpty
    @NotNull(message = "Fecha final es obligatoria")
    @Column(nullable = false)
    private String final_date;
}
