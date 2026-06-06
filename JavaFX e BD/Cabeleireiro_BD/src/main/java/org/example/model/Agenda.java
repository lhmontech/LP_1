package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
    private Long id;
    private Long clienteId;
    private Long servicoId;
    private LocalDate data;
    private LocalTime horario;

    public Agenda(Long id, Long clienteId, Long servicoId, LocalDate data, LocalTime horario) {
        this.id = id;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.data = data;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
