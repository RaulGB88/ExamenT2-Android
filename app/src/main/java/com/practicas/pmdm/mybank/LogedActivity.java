package com.practicas.pmdm.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.practicas.pmdm.mybank.adapter.ProductosArrayAdapter;
import com.practicas.pmdm.mybank.dao.ClienteDAO;
import com.practicas.pmdm.mybank.dao.ProductoDAO;
import com.practicas.pmdm.mybank.pojo.Cliente;
import com.practicas.pmdm.mybank.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

public class LogedActivity extends AppCompatActivity {

    ProductoDAO productoDAO = new ProductoDAO();
    ListView lvMenu;
    ProductosArrayAdapter<Producto> adapter = null;
    List<Producto> listProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged);
        init();
    }

    public void goHome(View view) {

        Intent pageReturned = new Intent(LogedActivity.this, MainActivity.class);
        startActivity(pageReturned);
    }

    private void init() {
        // Get list for layout.
        lvMenu = findViewById(R.id.lvList);
        // Get Data.
        listProducto = productoDAO.getAll();
        // Init Adapter.
        adapter = new ProductosArrayAdapter<>(this, listProducto);
        // Set Adapter to List.
        lvMenu.setAdapter(adapter);
    }

    public void chargeProducts() {

        // Get all clients.
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> listProducto = productoDAO.getAll();
    }
/*
    private void init() {
        // Get list for layout.
        lvMenu = findViewById(R.id.lvList);
        // Get Data.
        listInstalacion = instalacionesDao.getPlateList();
        // Init Adapter.
        adapter = new InstalacionArrayAdapter<>(this, listInstalacion);
        // Set Adapter to List.
        lvMenu.setAdapter(adapter);
    }


    private void recieveData() {
        Bundle extras = getIntent().getExtras();
        String dni = (String) extras.get("dni");

        txtUserDNI = (TextView) findViewById(R.id.txtUserDNI);
        txtUserDNI.setText(dni);
    }
     */
}