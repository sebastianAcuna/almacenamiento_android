package com.example.almacenamiento_android.bd;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.almacenamiento_android.clases.Donante;
import com.example.almacenamiento_android.clases.Usuario;

import java.util.List;


@Dao
public interface MyDao {


    @Insert
    public long setDonante(Donante donante);

    @Insert
    public long setUsuario(Usuario usuario);


    @Query("SELECT * FROM donante ")
    public List<Donante> getDonantes();



    @Query("SELECT * FROM Usuario")
    public List<Usuario> getUsuarios();

    @Query("SELECT * FROM Usuario WHERE nombre_usuario = :nombre AND pass_usuario = :pass")
    public Usuario getUsuarioLogin(String nombre, String pass);

    @Query("SELECT * FROM Usuario WHERE nombre_usuario = :nombre")
    public Usuario getUsuarioByName(String nombre);




//    @Query("UPDATE fotos_usuarios SET estado_foto = 1 WHERE id_foto_usuario = :idFoto")
//    public long updateFotosSubidas(int idFoto);
//    @Delete
//    public void deleteUnaAsistencia(Asistencia asistencia);


}
