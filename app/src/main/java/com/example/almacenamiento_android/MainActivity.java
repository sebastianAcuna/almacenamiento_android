package com.example.almacenamiento_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.almacenamiento_android.bd.MyAppBD;
import com.example.almacenamiento_android.clases.Usuario;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button ingresar, agregar;
    private EditText et_user_login, et_pass_login;
    public static MyAppBD myAppDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = (Button) findViewById(R.id.btn_ingresar);
        agregar = (Button) findViewById(R.id.btn_nuevo_usuario);
        et_user_login = (EditText) findViewById(R.id.et_user_login);
        et_pass_login = (EditText) findViewById(R.id.et_pass_login);


        myAppDB = Room.databaseBuilder(getApplicationContext(),MyAppBD.class,"donantes.db").allowMainThreadQueries().build();



        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_user_login.getText().toString()) || TextUtils.isEmpty(et_pass_login.getText().toString())){
                    Toast.makeText(MainActivity.this, "Debe llenar todos los campos para ingresar ", Toast.LENGTH_SHORT).show();
                }else{
                    Usuario usuario  = myAppDB.myDao().getUsuarioLogin(et_user_login.getText().toString(), et_pass_login.getText().toString());
                    if (usuario != null){
                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, principal_activity.class));
                    }else{
                        Toast.makeText(MainActivity.this, "usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoUsuarioDialog();
            }
        });
    }




    public void nuevoUsuarioDialog( ){
        final Dialog builder = new Dialog(MainActivity.this);
        builder.setCancelable(false);
        View viewInfalted = LayoutInflater.from(this).inflate(R.layout.layout_nuevo_usuario,null);
        builder.setContentView(viewInfalted);

        final EditText nombreusuario  = (EditText) viewInfalted.findViewById(R.id.nombre_usuario_login);
        final EditText pass1  = (EditText) viewInfalted.findViewById(R.id.pass_user_login);
        final EditText pass2 = (EditText) viewInfalted.findViewById(R.id.pass_confirm_login);

        Button btn_cancelar = (Button) viewInfalted.findViewById(R.id.btn_cancelar);
        Button btn_guardar = (Button) viewInfalted.findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nombreusuario.getText().toString()) || TextUtils.isEmpty(pass1.getText().toString()) || TextUtils.isEmpty(pass2.getText().toString())){
                    Toast.makeText(MainActivity.this, "Debe llenar todos los campos ", Toast.LENGTH_SHORT).show();
                }else{
                    if(!TextUtils.equals(pass1.getText().toString(), pass2.getText().toString())){
                        Toast.makeText(MainActivity.this, "Ambas contraseñas deben ser iguales ", Toast.LENGTH_SHORT).show();

                    }else{
                        Usuario usuario = myAppDB.myDao().getUsuarioByName(nombreusuario.getText().toString());
                        if (usuario != null){
                            Toast.makeText(MainActivity.this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                        }else{
                            Usuario user = new Usuario(nombreusuario.getText().toString(), pass1.getText().toString());
                            try{
                                long id = myAppDB.myDao().setUsuario(user);
                                if (id > 0){
                                    Toast.makeText(MainActivity.this, "Usuario ingresado con exito", Toast.LENGTH_SHORT).show();
                                    builder.dismiss();
                                }
                            }catch (Exception e){
                                Toast.makeText(MainActivity.this, "No hemos podido registraral usuario", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });

        builder.show();

    }
}
