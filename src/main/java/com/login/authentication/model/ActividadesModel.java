package com.login.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.login.authentication.model.ScheduleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activities")
public class ActividadesModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank(message = "Data no puede estar vacio")
    @NotNull
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSch", referencedColumnName = "idSch")
    @JsonIgnore
    private ScheduleModel scheduleModel;

    @NotEmpty
    @NotBlank(message = "horas no puede estar vacio")
    @NotNull
    private String hours;

    @NotEmpty
    @NotBlank(message = "Fecha inicial no puede estar vacio")
    @NotNull
    private String initial_date;

    @NotEmpty
    @NotBlank(message = "Fecha final no puede estar vacio")
    @NotNull
    private String final_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(String initial_date) {
        this.initial_date = initial_date;
    }

    public String getFinal_date() {
        return final_date;
    }

    public void setFinal_date(String final_date) {
        this.final_date = final_date;
    }
}
