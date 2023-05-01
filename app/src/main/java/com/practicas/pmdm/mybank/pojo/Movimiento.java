package com.practicas.pmdm.mybank.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by loren on 07/10/15.
 */
public class Movimiento implements Serializable {

    private int id;
    private int tipo;
    private Date fechaOperacion;
    private String descripcion;
    private float importe;
    private Producto productoOrigen;
    private Producto productoDestino;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Producto getProductoOrigen() {
        return productoOrigen;
    }

    public void setProductoOrigen(Producto productoOrigen) {
        this.productoOrigen = productoOrigen;
    }

    public Producto getProductoDestino() {
        return productoDestino;
    }

    public void setProductoDestino(Producto productoDestino) {
        this.productoDestino = productoDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movimiento(int id, int tipo, Date fechaOperacion, String descripcion, float importe, Producto productoOrigen, Producto productoDestino) {
        this.id = id;
        this.tipo = tipo;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.importe = importe;
        this.productoOrigen = productoOrigen;
        this.productoDestino = productoDestino;
    }

    public Movimiento() {
        super();
    }

    @Override
    public String toString() {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "id: " + this.id + "\ntipo: " + this.tipo + "\nfecha operacion: " + formateador.format(this.fechaOperacion) + "\ndescripcion: " + this.descripcion + "\nimporte: " +
                this.importe + "\nid producto origen: " + this.productoOrigen.getId() + "\nid producto destino: " + this.productoDestino.getId();
    }
}
