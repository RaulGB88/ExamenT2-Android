package com.practicas.pmdm.mybank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.practicas.pmdm.mybank.R;
import com.practicas.pmdm.mybank.pojo.Producto;

import java.util.List;

public class ProductosArrayAdapter<Producto>  extends ArrayAdapter<Producto> {

    public ProductosArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ProductosArrayAdapter(@NonNull Context context, List<Producto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            listItemView = inflater.inflate(R.layout.list_row, parent, false);
        }

        //Obteniendo instancias de los text views y del ImageView
        TextView name = (TextView) listItemView.findViewById(R.id.tvName);
        ImageView image = (ImageView) listItemView.findViewById(R.id.ivImage);

        //Obteniendo instancia de la Tarea en la posición actual
        Producto producto = (Producto) getItem(position);

        // name.setText(producto.getName());
        // image.setImageResource(producto.getImage());

        //Dividir la cadena en Nombre y Hora
        String cadena;
        String subCadenas [];
        String delimitador = "/";

        cadena = producto.toString();
        subCadenas = cadena.split(delimitador,4);

        name.setText(subCadenas[0]);
        //image.setImageResource(Integer.parseInt(subCadenas[1]));

        return listItemView;
    }
}
