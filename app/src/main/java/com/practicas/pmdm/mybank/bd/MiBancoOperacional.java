package com.practicas.pmdm.mybank.bd;

import android.content.Context;

import com.practicas.pmdm.mybank.pojo.Cliente;
import com.practicas.pmdm.mybank.pojo.Producto;
import com.practicas.pmdm.mybank.pojo.Movimiento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loren on 08/10/15.
 */
public class MiBancoOperacional {


    private MiBD miBD;

    protected MiBancoOperacional(Context context) {
        miBD = MiBD.getInstance(context);
    }

    private static MiBancoOperacional instance = null;

    //***************************************
    // Interfaz publica de la API del banco
    //***************************************

    // Constructor del banco. Obtiene una instancia del mismo para operar
    public static MiBancoOperacional getInstance(Context context) {
        if (instance == null) {
            instance = new MiBancoOperacional(context);
        }
        return instance;
    }

    // Operacion Login: Verifica que el cliente existe y que su contraseña es correcta. Recibira un cliente
    // que solo contendrá el nif y la password.
    public Cliente login(Cliente c) {
        Cliente aux = (Cliente) miBD.getClienteDAO().search(c);
        if (aux == null) {
            return null;
        } else if (aux.getClaveSeguridad().equals(c.getClaveSeguridad())) {
            return aux;
        } else {
            return null;
        }
    }

    // Operacion changePassword: Cambia la password del cliente. Recibirá el cliente de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    public int changePassword(Cliente c) {
        int resultado = miBD.getClienteDAO().update(c);
        if (resultado == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    // Operacion getProductos: Obtiene un ArrayList de las productos de un cliente que recibe como parámetro
    public List<Producto> getProductos(Cliente c) {
        return miBD.getProductoDAO().getProductos(c);
    }

    // Operacion getMovimientos: Obtiene un ArrayList de los movimientos de una producto que recibe como parámetro
    public ArrayList<Movimiento> getMovimientos(Producto c) {
        return miBD.getMovimientoDAO().getMovimientos(c);
    }

    /* Operacion transferencia: Desde una producto hace transferencia a otra producto, siempre que en la producto origen haya dinero disponible.

       Restricciones:

         - La comprobacion de la existencia de la producto destino se realizará dentro del método. La producto de origen existe por defecto, ya que el alumno ha de poder seleccionarla.
         - En caso de no existir la producto destino se devolvera como entero un 1.
         - La fecha de la operación será la fecha del sistema. Recordar que es almacenada como un long.
         - No se permitirá realizar una transferencia si la producto se queda en negativo. En este caso se devolvera como entero un 2.
         - Solo se permiten movimiento en las productos locales al banco, por lo que ambas productos deben existir.
         - La operación se ha de ver reflejada en las dos productos: el descuento en una y el ingreso en otra.
         - El campo tipo en la tabla de movimientos indica como es el movimiento. 0 indica que es un descuento, 1 indica que es un ingreso y 2 indica que es un reintegro por un cajero.
         - El movimiento que viene como parametro en el metodo, que viene en la variable movimientoTransferencia ha de ser de tipo 0.
         - Si la operacion es correcta se devuelve un 0
    */
    public int transferencia(Movimiento movimientoTransferencia) {
        return 0;
    }

    // Operacion getMovimientosTipo: Obtiene un ArrayList de los movimientos de un tipo específico de una producto que recibe como parámetro
    public ArrayList<Movimiento> getMovimientosTipo(Producto c, int tipo) {
        return miBD.getMovimientoDAO().getMovimientosTipo(c, tipo);
    }
}
