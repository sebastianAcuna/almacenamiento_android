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
    private String tipoSangreDonante;
    @ColumnInfo(name = "elemento")
    private String elemento;
    @ColumnInfo(name = "peso_donante")
    private double pesoDonante;
    @ColumnInfo(name = "estatura_donante")
    private double estaturaDonante;


    public Donante(int idDonante, String nombreDonante, String apellidoDonante, int edadDonante, String tipoSangreDonante, String elemento, double pesoDonante, double estaturaDonante) {
        this.idDonante = idDonante;
        this.nombreDonante = nombreDonante;
        this.apellidoDonante = apellidoDonante;
        this.edadDonante = edadDonante;
        this.tipoSangreDonante = tipoSangreDonante;
        this.elemento = elemento;
        this.pesoDonante = pesoDonante;
        this.estaturaDonante = estaturaDonante;
    }

    public Donante() {
    }

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

    public String getTipoSangreDonante() {
        return tipoSangreDonante;
    }

    public void setTipoSangreDonante(String tipoSangreDonante) {
        this.tipoSangreDonante = tipoSangreDonante;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
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
