package com.example.almacenamiento_android.bd;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.almacenamiento_android.clases.Donante;
import com.example.almacenamiento_android.clases.Usuario;

import java.util.List;


@Dao
public interface MyDao {


    @Insert
    public long setDonante(Donante donante);

    @Insert
    public long setUsuario(Usuario usuario);


    @Update
    int updateDonante(Donante donante);

    @Update
    int updateUsuario(Usuario usuario);

    @Delete
    void deleteDonante(Donante donante);


    @Query("DELETE FROM donante")
    void deleteDonantes();

    @Query("DELETE FROM usuario WHERE id_usuario = :id")
    void deleteUsuario(int id);

    @Query("SELECT * FROM donante ")
    public List<Donante> getDonantes();

    @Query("SELECT * FROM donante WHERE id_donante = :id")
    public Donante getDonantesById(int id);

    @Query("SELECT * FROM usuario WHERE id_usuario = :id AND pass_usuario = :pass")
    public Usuario getusuarioByIdAndPass(int id, String pass);



    @Query("SELECT * FROM Usuario")
    public List<Usuario> getUsuarios();

    @Query("SELECT * FROM Usuario WHERE nombre_usuario = :nombre AND pass_usuario = :pass")
    public Usuario getUsuarioLogin(String nombre, String pass);

    @Query("SELECT * FROM Usuario WHERE nombre_usuario = :nombre")
    public Usuario getUsuarioByName(String nombre);





}
