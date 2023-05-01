package com.practicas.pmdm.mybank.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.practicas.pmdm.mybank.bd.MiBD;
import com.practicas.pmdm.mybank.pojo.Cliente;
import com.practicas.pmdm.mybank.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Producto c = (Producto) obj;

        contentValues.put("nombre", c.getNombre());
        contentValues.put("precio", c.getPrecio());
        contentValues.put("stock", c.getStock());
        return MiBD.getDB().insert("productos", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Producto c = (Producto) obj;
        contentValues.put("nombre", c.getNombre());
        contentValues.put("precio", c.getPrecio());
        contentValues.put("stock", c.getStock());
        contentValues.put("idcliente", c.getCliente().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MiBD.getDB().update("productos", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Producto c = (Producto) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("productos", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Producto c = (Producto) obj;
        String condicion = "";
        if (TextUtils.isEmpty(c.getNombre())) {
            condicion = "id=" + String.valueOf(c.getId());
        }

        String[] columnas = {
                "id", "nombre", "precio", "stock", "idcliente"
        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setNombre(cursor.getString(1));
            c.setPrecio(cursor.getFloat(2));
            c.setStock(cursor.getInt(3));

            // Obtenemos el cliente y lo asignamos
            Cliente a = new Cliente();
            a.setId(cursor.getInt(6));
            a = (Cliente) MiBD.getInstance(null).getClienteDAO().search(a);
            c.setCliente(a);

            // Obtenemos la lista de movimientos y los asignamos
            //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

            return c;
        } else {
            return null;
        }
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> listaProductos = new ArrayList<Producto>();
        String[] columnas = {
                "id", "nombre", "precio", "stock"
        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Producto c = new Producto();
                c.setId(cursor.getInt(0));
                c.setNombre(cursor.getString(1));
                c.setPrecio(cursor.getFloat(2));
                c.setStock(cursor.getInt(3));

                // Obtenemos el cliente y lo asignamos
                /*
                Cliente a = new Cliente();
                a.setId(cursor.getInt(6));
                a = (Cliente) MiBD.getInstance(null).getClienteDAO().search(a);
                c.setCliente(a);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));
                */
                listaProductos.add(c);

            } while (cursor.moveToNext());
        }
        return listaProductos;
    }

    public List<Producto> getProductos(Cliente cliente) {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        String condicion = "idcliente=" + String.valueOf(cliente.getId());
        String[] columnas = {
                "id", "nombre", "precio", "dc", "numeroproducto", "saldoactual", "idcliente"
        };
        Cursor cursor = MiBD.getDB().query("productos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Producto c = new Producto();
                c.setId(cursor.getInt(0));
                c.setNombre(cursor.getString(1));
                c.setPrecio(cursor.getFloat(2));
                c.setStock(cursor.getInt(3));

                c.setCliente(cliente);

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));

                listaProductos.add(c);

            } while (cursor.moveToNext());
        }
        return listaProductos;
    }

}
