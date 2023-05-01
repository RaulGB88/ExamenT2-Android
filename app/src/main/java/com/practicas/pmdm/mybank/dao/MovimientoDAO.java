package com.practicas.pmdm.mybank.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.practicas.pmdm.mybank.bd.MiBD;
import com.practicas.pmdm.mybank.pojo.Producto;

import java.util.ArrayList;
import java.util.Date;

import com.practicas.pmdm.mybank.pojo.Movimiento;

/**
 * Created by loren on 08/10/15.
 */
public class MovimientoDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movimiento c = (Movimiento) obj;
        contentValues.put("tipo", c.getTipo());
        contentValues.put("fechaoperacion", c.getFechaOperacion().getTime());
        contentValues.put("descripcion", c.getDescripcion());
        contentValues.put("importe", c.getImporte());
        contentValues.put("idproductoorigen", c.getProductoOrigen().getId());
        contentValues.put("idproductodestino", c.getProductoDestino().getId());

        return MiBD.getDB().insert("movimientos", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movimiento c = (Movimiento) obj;
        contentValues.put("tipo", c.getTipo());
        contentValues.put("fechaoperacion", c.getFechaOperacion().getTime());
        contentValues.put("descripcion", c.getDescripcion());
        contentValues.put("importe", c.getImporte());
        contentValues.put("idproductoorigen", c.getProductoOrigen().getId());
        contentValues.put("idproductodestino", c.getProductoDestino().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MiBD.getDB().update("movimientos", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Movimiento c = (Movimiento) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el producto indicado en el campo de texto
        MiBD.getDB().delete("movimientos", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Movimiento c = (Movimiento) obj;
        String condicion = "id=" + String.valueOf(c.getId());
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idproductoorigen", "idproductodestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setTipo(cursor.getInt(1));
            c.setFechaOperacion(new Date(cursor.getLong(2)));
            c.setDescripcion(cursor.getString(3));
            c.setImporte(cursor.getFloat(4));

            // Asignamos la producto de origen
            Producto a = new Producto();
            a.setId(cursor.getInt(5));
            c.setProductoOrigen((Producto) MiBD.getInstance(null).getProductoDAO().search(a));

            // Asignamos la producto de destino
            int aux = cursor.getInt(6);
            if (aux == -1) {
                a.setId(-1);
            } else {
                a.setId(aux);
                c.setProductoOrigen((Producto) MiBD.getInstance(null).getProductoDAO().search(a));
            }

            return c;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idproductoorigen", "idproductodestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la producto de origen
                Producto a = new Producto();
                a.setId(cursor.getInt(5));
                c.setProductoOrigen((Producto) MiBD.getInstance(null).getProductoDAO().search(a));


                // Asignamos la producto de destino
                a = new Producto();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductoOrigen(a);
                } else {
                    a.setId(aux);
                    c.setProductoDestino((Producto) MiBD.getInstance(null).getProductoDAO().search(a));
                }


                listaMovimientos.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovimientos;
    }

    public ArrayList getMovimientos(Producto producto) {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String condicion = "idproductoorigen=" + String.valueOf(producto.getId());
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idproductoorigen", "idproductodestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la producto de origen
                c.setProductoOrigen(producto);


                // Asignamos la producto de destino
                Producto a = new Producto();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductoDestino(a);
                } else {
                    a.setId(aux);
                    a = (Producto) MiBD.getInstance(null).getProductoDAO().search(a);
                    c.setProductoDestino(a);
                }

                listaMovimientos.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovimientos;
    }

    public ArrayList getMovimientosTipo(Producto producto, int tipo) {
        ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        String condicion = "idproductoorigen=" + String.valueOf(producto.getId()) + " AND tipo = " + String.valueOf(tipo);
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idproductoorigen", "idproductodestino"
        };
        Cursor cursor = MiBD.getDB().query("movimientos", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movimiento c = new Movimiento();
                c.setId(cursor.getInt(0));
                c.setTipo(cursor.getInt(1));
                c.setFechaOperacion(new Date(cursor.getLong(2)));
                c.setDescripcion(cursor.getString(3));
                c.setImporte(cursor.getFloat(4));

                // Asignamos la producto de origen
                c.setProductoOrigen(producto);


                // Asignamos la producto de destino
                Producto a = new Producto();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductoDestino(a);
                } else {
                    a.setId(aux);
                    a = (Producto) MiBD.getInstance(null).getProductoDAO().search(a);
                    c.setProductoDestino(a);
                }

                listaMovimientos.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovimientos;
    }
}
