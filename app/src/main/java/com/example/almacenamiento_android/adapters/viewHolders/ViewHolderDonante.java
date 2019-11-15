package com.example.almacenamiento_android.adapters.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.almacenamiento_android.R;
import com.example.almacenamiento_android.adapters.AdapterDonante;
import com.example.almacenamiento_android.clases.Donante;

public class ViewHolderDonante extends RecyclerView.ViewHolder {

private TextView nombre_donante_lista, id_donante_lista,edad_donante_lista,tipo_sangre_donante_lista,peso_donante_lista;
private ImageView trash_delete,pencil_edit;

    public ViewHolderDonante(@NonNull View itemView) {
        super(itemView);

        nombre_donante_lista = (TextView) itemView.findViewById(R.id.nombre_donante_lista);
        id_donante_lista = (TextView) itemView.findViewById(R.id.id_donante_lista);
        edad_donante_lista = (TextView) itemView.findViewById(R.id.edad_donante_lista);
        tipo_sangre_donante_lista = (TextView) itemView.findViewById(R.id.tipo_sangre_donante_lista);
        peso_donante_lista = (TextView) itemView.findViewById(R.id.peso_donante_lista);
        pencil_edit = (ImageView) itemView.findViewById(R.id.pencil_edit);
        trash_delete = (ImageView) itemView.findViewById(R.id.trash_delete);
    }

    public void bind(final Donante donante, final AdapterDonante.OnItemClickListener click){

        nombre_donante_lista.setText(donante.getNombreDonante());
        id_donante_lista.setText("Id : "+donante.getIdDonante());
        edad_donante_lista.setText("Edad :" + donante.getEdadDonante());
        tipo_sangre_donante_lista.setText("Sangre "+donante.getTipoSangreDonante() + " "+donante.getElemento());
        peso_donante_lista.setText("Peso: "+donante.getPesoDonante()+"Kg Estatura : "+donante.getEstaturaDonante()+"Cm");


        trash_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onItemClick(donante, 2);
            }
        });

        pencil_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onItemClick(donante, 1);
            }
        });

    }

}
