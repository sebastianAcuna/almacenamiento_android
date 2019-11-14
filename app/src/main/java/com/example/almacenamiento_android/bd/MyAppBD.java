package com.example.almacenamiento_android.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.almacenamiento_android.clases.Donante;
import com.example.almacenamiento_android.clases.Usuario;

@Database(entities = { Donante.class, Usuario.class}, version = 1)
public abstract class MyAppBD extends RoomDatabase {

        public abstract MyDao myDao();

}
