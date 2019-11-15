package com.example.almacenamiento_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.almacenamiento_android.adapters.AdapterDonante;
import com.example.almacenamiento_android.bd.MyAppBD;
import com.example.almacenamiento_android.clases.Donante;
import com.example.almacenamiento_android.clases.Usuario;
import com.example.almacenamiento_android.clases.utilidad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class principal_activity extends AppCompatActivity {

    private FloatingActionButton botonAdd;
    public static MyAppBD myAppDB;
    private SearchView buscarDetAS;

    ArrayAdapter<String> comboAdapter;
    ArrayAdapter<String> elementoAdapter;

    private RecyclerView recyclerView;

    private AdapterDonante adapterDonante;
    private RecyclerView.LayoutManager lManager;
    private SharedPreferences sharedPreferences;


    String[] arrayTipoSangre;
    String[] arrayElemento;

    private String idTipoSangre, idElemento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_activity);


        botonAdd = (FloatingActionButton) findViewById(R.id.add_donante);
        recyclerView = (RecyclerView) findViewById(R.id.lista_donantes);
        buscarDetAS = findViewById(R.id.buscador);

        lManager = new LinearLayoutManager(this);



        myAppDB = Room.databaseBuilder(getApplicationContext(), MyAppBD.class,"donantes.db").allowMainThreadQueries().build();


        sharedPreferences = getSharedPreferences(utilidad.SHARED, MODE_PRIVATE);

        botonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoUsuarioDialog(new Donante());
            }
        });



        mostrarDonantes();




        buscarDetAS.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapterDonante!= null){
                    adapterDonante.getFilter().filter(newText);
                }

                if (adapterDonante!= null){
                    adapterDonante.getFilter().filter(newText);
                }
                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.cambiar_pass:
                final Dialog dialog = new Dialog(this);

                View viewInfalted = LayoutInflater.from(this).inflate(R.layout.cambiar_pass,null);
                dialog.setContentView(viewInfalted);

                final EditText pasActual = viewInfalted.findViewById(R.id.pass_actual);
                final EditText pasNueva = viewInfalted.findViewById(R.id.nueva_pass);
                final EditText passnuevaRepeat = viewInfalted.findViewById(R.id.nueva_pass_repeat);
                Button btn_aceptar= viewInfalted.findViewById(R.id.btn_guardar);
                Button btn_cancelar= viewInfalted.findViewById(R.id.btn_cancelar);



                btn_aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(pasActual.getText().toString()) || TextUtils.isEmpty(pasNueva.getText().toString()) || TextUtils.isEmpty(passnuevaRepeat.getText().toString())){
                            Toast.makeText(principal_activity.this, "Debe completar todos los cambios", Toast.LENGTH_SHORT).show();
                        }else{

                            if (!TextUtils.equals(pasNueva.getText().toString(), passnuevaRepeat.getText().toString())){
                                Toast.makeText(principal_activity.this, "Contraseña nueva y su repeticion deben ser iguales", Toast.LENGTH_SHORT).show();
                            }else{
                                Usuario usuario = myAppDB.myDao().getusuarioByIdAndPass(sharedPreferences.getInt(utilidad.ID_USER, 0), pasActual.getText().toString());
                                if(usuario != null){
                                    usuario.setPasswordUsuario(pasNueva.getText().toString());
                                    try{
                                        int id = myAppDB.myDao().updateUsuario(usuario);
                                        if(id > 0){
                                            Toast.makeText(principal_activity.this, "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(principal_activity.this, "No se ha actualizado la contraseña", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(principal_activity.this, "No ingresaste la contraseña actual correcta", Toast.LENGTH_SHORT).show();
                                }



                            }
                        }
                    }
                });


                btn_cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


                break;

            case R.id.eliminar_cuenta:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("atencion");
                builder.setMessage("¿Estás seguro que deseas elminar la cuenta ? ");
                builder.setNegativeButton("Cancelar",null);
                builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try{
                            myAppDB.myDao().deleteUsuario(sharedPreferences.getInt(utilidad.ID_USER, 0));
                            myAppDB.myDao().deleteDonantes();

                            startActivity(new Intent(principal_activity.this, MainActivity.class));
                        }catch (Exception e){

                        }
                    }
                });

                builder.create().show();
                break;

            case R.id.cerrar_sesion:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("atencion");
                builder2.setMessage("¿Estás seguro que deseas cerrar sesion ? ");
                builder2.setNegativeButton("Cancelar",null);
                builder2.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().remove(utilidad.ID_USER).apply();
                        startActivity(new Intent(principal_activity.this, MainActivity.class));
                    }
                });

                builder2.create().show();
                break;
        }

        return false;
    }

    void mostrarDonantes(){
        List<Donante> donantes = myAppDB.myDao().getDonantes();
        adapterDonante = new AdapterDonante(donantes, new AdapterDonante.OnItemClickListener() {
            @Override
            public void onItemClick(final Donante item, int accion) {
                switch (accion){
                    case 1:
                        nuevoUsuarioDialog(item);
                        break;
                    case 2:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(principal_activity.this);
                        dialog.setTitle("Alerta");
                        dialog.setMessage("¿Estás seguro que deseas eliminar este donante ?");
                        dialog.setNegativeButton("cancelar",null);
                        dialog.setPositiveButton("elminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                myAppDB.myDao().deleteDonante(item);
                                adapterDonante.notifyDataSetChanged();
                            }
                        });

                        dialog.create().show();

                        break;
                }
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lManager);
        recyclerView.setAdapter(adapterDonante);
    }


    public void nuevoUsuarioDialog(Donante item){
        final Dialog builder = new Dialog(principal_activity.this);
        builder.setCancelable(false);
        View viewInfalted = LayoutInflater.from(this).inflate(R.layout.nuevo_donante_dialog,null);
        builder.setContentView(viewInfalted);

        final EditText number_id  = (EditText) viewInfalted.findViewById(R.id.number_id);
        final EditText nombre_donante  = (EditText) viewInfalted.findViewById(R.id.nombre_donante);
        final EditText apellido_donante  = (EditText) viewInfalted.findViewById(R.id.apellido_donante);
        final EditText edad_donante  = (EditText) viewInfalted.findViewById(R.id.edad_donante);
        final Spinner tipo_sangre  = (Spinner) viewInfalted.findViewById(R.id.tipo_sangre);
        final Spinner elemnento  = (Spinner) viewInfalted.findViewById(R.id.elemnento);
        final EditText peso_donante  = (EditText) viewInfalted.findViewById(R.id.peso_donante);
        final EditText estatura_donante = (EditText) viewInfalted.findViewById(R.id.estatura_donante);


        arrayTipoSangre = getResources().getStringArray(R.array.tipo_sangre_array);
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayTipoSangre);
        tipo_sangre.setAdapter(comboAdapter);

        arrayElemento = getResources().getStringArray(R.array.elemento_array);
        elementoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayElemento);
        elemnento.setAdapter(elementoAdapter);






        if (item != null){
            if (item.getIdDonante() > 0){

                number_id.setText(String.valueOf(item.getIdDonante()));
                number_id.setEnabled(false);

                nombre_donante.setText(item.getNombreDonante());
                apellido_donante.setText(item.getApellidoDonante());
                edad_donante.setText(String.valueOf(item.getEdadDonante()));
                peso_donante.setText(String.valueOf(item.getPesoDonante()));
                estatura_donante.setText(String.valueOf(item.getEstaturaDonante()));

                for(int i = 0; i < arrayTipoSangre.length; i++){
                    if(arrayTipoSangre[i].equals(item.getTipoSangreDonante())){
                        tipo_sangre.setSelection(i);
                    }
                }

                for(int i = 0; i < arrayElemento.length; i++){
                    if(arrayElemento[i].equals(item.getElemento())){
                        elemnento.setSelection(i);
                    }
                }


            }


        }



        tipo_sangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTipoSangre = tipo_sangre.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        elemnento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idElemento = elemnento.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btn_cancelar_donante = (Button) viewInfalted.findViewById(R.id.btn_cancelar_donante);
        Button btn_guardar_donante = (Button) viewInfalted.findViewById(R.id.btn_guardar_donante);

        btn_guardar_donante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                    TextUtils.isEmpty(number_id.getText().toString()) ||
                    TextUtils.isEmpty(nombre_donante.getText().toString()) ||
                    TextUtils.isEmpty(apellido_donante.getText().toString()) ||
                    TextUtils.isEmpty(edad_donante.getText().toString()) ||
                    TextUtils.isEmpty(peso_donante.getText().toString()) ||
                    TextUtils.isEmpty(estatura_donante.getText().toString())
                ){
                    Toast.makeText(principal_activity.this, "Debe llenar todos los campos ", Toast.LENGTH_SHORT).show();
                }else{

                    Donante donante = myAppDB.myDao().getDonantesById(Integer.parseInt(number_id.getText().toString()));

                    Donante donante1 = new Donante(
                            Integer.parseInt(number_id.getText().toString()),
                            nombre_donante.getText().toString(),
                            apellido_donante.getText().toString(),
                            Integer.parseInt(edad_donante.getText().toString()),
                            idTipoSangre,
                            idElemento,
                            Double.parseDouble(peso_donante.getText().toString()),
                            Double.parseDouble(estatura_donante.getText().toString()));

                    if(donante != null){
                        try{
                            int id = myAppDB.myDao().updateDonante(donante1);
                            if(id > 0){
                                Toast.makeText(principal_activity.this, "Donante actualizado con exito", Toast.LENGTH_SHORT).show();
                                mostrarDonantes();
                                if(adapterDonante != null){adapterDonante.notifyDataSetChanged();}
                            }

                            builder.dismiss();

                        }catch (Exception e){
                            Toast.makeText(principal_activity.this, "No se ha podido actualizar al donante", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        try{
                            myAppDB.myDao().setDonante(donante1);
                            Toast.makeText(principal_activity.this, "Donante insertado correctamente", Toast.LENGTH_SHORT).show();
                            mostrarDonantes();
                            if(adapterDonante != null){adapterDonante.notifyDataSetChanged();}
                            builder.dismiss();
                        }catch (Exception e){
                            Toast.makeText(principal_activity.this, "No se ha podido insertar al donante", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btn_cancelar_donante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });

        builder.show();

    }

}
