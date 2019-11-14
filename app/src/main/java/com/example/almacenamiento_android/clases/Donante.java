package com.example.almacenamiento_android.clases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "donante")
public class Donante {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_donante")
    private int idDonante;
    @ColumnInfo(name = "nombre_donante")
    private String nombreDonante;
    @ColumnInfo(name = "apellido_donante")
    private String apellidoDonante;
    @ColumnInfo(name = "edad_donante")
    private int edadDonante;
    @ColumnInfo(name = "tipo_sangre_donante")
    private int tipoSangreDonante;
    @ColumnInfo(name = "elemento")
    private int elemento;
    @ColumnInfo(name = "peso_donante")
    private double pesoDonante;
    @ColumnInfo(name = "estatura_donante")
    private double estaturaDonante;


    public int getIdDonante() {
        return idDonante;
    }

    public void setIdDonante(int idDonante) {
        this.idDonante = idDonante;
    }

    public String getNombreDonante() {
        return nombreDonante;
    }

    public void setNombreDonante(String nombreDonante) {
        this.nombreDonante = nombreDonante;
    }

    public String getApellidoDonante() {
        return apellidoDonante;
    }

    public void setApellidoDonante(String apellidoDonante) {
        this.apellidoDonante = apellidoDonante;
    }

    public int getEdadDonante() {
        return edadDonante;
    }

    public void setEdadDonante(int edadDonante) {
        this.edadDonante = edadDonante;
    }

    public int getTipoSangreDonante() {
        return tipoSangreDonante;
    }

    public void setTipoSangreDonante(int tipoSangreDonante) {
        this.tipoSangreDonante = tipoSangreDonante;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public double getPesoDonante() {
        return pesoDonante;
    }

    public void setPesoDonante(double pesoDonante) {
        this.pesoDonante = pesoDonante;
    }

    public double getEstaturaDonante() {
        return estaturaDonante;
    }

    public void setEstaturaDonante(double estaturaDonante) {
        this.estaturaDonante = estaturaDonante;
    }
}
