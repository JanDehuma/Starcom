package com.dantefx.starcom;

import java.util.Date;

public class TareaModel {
    private int id;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaEntrega;
    private String prioridad;
    private int idUsuario;

    public void Tarea(int id, String nombre, String descripcion, boolean estado, Date fechaEntrega, String prioridad, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.prioridad = prioridad;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
