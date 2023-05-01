package com.practicas.pmdm.mybank.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.practicas.pmdm.mybank.dao.ClienteDAO;
import com.practicas.pmdm.mybank.dao.ProductoDAO;
import com.practicas.pmdm.mybank.dao.MovimientoDAO;

/**
 * Created by loren on 07/10/15.
 */
public class MiBD extends SQLiteOpenHelper {

    private static SQLiteDatabase db;
    //nombre de la base de datos
    private static final String database = "MiBanco";
    //versión de la base de datos
    private static final int version = 11;
    //Instrucción SQL para crear la tabla de Clientes
    private String sqlCreacionClientes = "CREATE TABLE clientes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nif STRING, nombre STRING, " +
            "apellidos STRING, claveSeguridad STRING, email STRING);";
    //Instruccion SQL para crear la tabla de Productos
    private String sqlCreacionProductos = "CREATE TABLE productos ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, precio FLOAT, " +
            "stock INTEGER);";
    //Instruccion SQL para crear la tabla de movimientos
    private String sqlCreacionMovimientos = "CREATE TABLE movimientos ( id INTEGER PRIMARY KEY AUTOINCREMENT, tipo INTEGER, fechaoperacion LONG," +
            " descripcion STRING, importe FLOAT, idproductoorigen INTEGER, idproductodestino INTEGER);";


    public static MiBD instance = null;

    private static ClienteDAO clienteDAO;
    private static ProductoDAO productoDAO;

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public MovimientoDAO getMovimientoDAO() {
        return movimientoDAO;
    }

    private static MovimientoDAO movimientoDAO;

    public static MiBD getInstance(Context context) {
        if (instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            clienteDAO = new ClienteDAO();
            productoDAO = new ProductoDAO();
            movimientoDAO = new MovimientoDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB() {
        return db;
    }

    public static void closeDB() {
        db.close();
    }

    ;

    /**
     * Constructor de clase
     */
    public MiBD(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionClientes);
        db.execSQL(sqlCreacionProductos);
        db.execSQL(sqlCreacionMovimientos);

        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion);
        if (newVersion > oldVersion) {
            //elimina tabla
            db.execSQL("DROP TABLE IF EXISTS clientes");
            db.execSQL("DROP TABLE IF EXISTS productos");
            db.execSQL("DROP TABLE IF EXISTS movimientos");
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionClientes);
            db.execSQL(sqlCreacionProductos);
            db.execSQL(sqlCreacionMovimientos);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion);
        }
    }

    private void insercionDatos(SQLiteDatabase db) {
        // Insertamos los clientes
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (1, '11111111A', 'Filemón', 'Pí', '1234', 'filemon.pi@tia.es');");
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (2, '22222222B', 'Mortadelo', 'Ibáñez', '1234', 'mortadelo.ibanez@tia.es');");
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (3, '33333333C', 'Vicente', 'Mondragón', '1234', 'vicente.mondragon@tia.es');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, '44444444D', 'Ayrton', 'Senna', '1234', 'ayrton.senna@f1.es');");
        db.execSQL("INSERT INTO clientes(rowid, id, nif, nombre, apellidos, claveSeguridad, email)VALUES(null, null, 'B1111111A', 'Ibertrola', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B2222222B', 'Gas Natural', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B3333333C', 'Telefónica', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B4444444D', 'Aguas de Valencia', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B5555555E', 'Audi', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B6666666F', 'BMW', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B7777777G', 'PayPal', '-', '1234', '-');");
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B8888888H', 'Ayuntamiento de Valencia', '-', '1234', '-');");

        // Insertamos las productos
        db.execSQL("INSERT INTO productos (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan de pueblo', 0.9, 20);");
        db.execSQL("INSERT INTO productos (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan clasico', 0.8, 40);");
        db.execSQL("INSERT INTO productos (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan integral', 0.85, 12);");
        db.execSQL("INSERT INTO productos (rowid, id, nombre, precio, stock) VALUES (null, null, 'croisant', 1.2, 15);");
        db.execSQL("INSERT INTO productos (rowid, id, nombre, precio, stock) VALUES (null, null, 'magdalena', 1.1, 18);");

        // Insertamos los movimientos
        //db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idproductoorigen, idproductodestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -73.87, 1, 5);");
    }

}