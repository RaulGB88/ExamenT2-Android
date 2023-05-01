package com.practicas.pmdm.mybank.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {

    private int id;
    private String nombre;
    private float precio;
    private int stock;
    private Cliente cliente;
    private ArrayList<Movimiento> listaMovimientos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public Producto(int id, String nombre, float precio, int stock, Cliente cliente, ArrayList<Movimiento> listaMovimientos) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.cliente = cliente;
        this.listaMovimientos = listaMovimientos;
    }

    public Producto() {
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

}
