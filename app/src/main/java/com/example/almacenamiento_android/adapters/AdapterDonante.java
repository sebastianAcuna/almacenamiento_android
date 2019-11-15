package com.example.almacenamiento_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.almacenamiento_android.R;
import com.example.almacenamiento_android.adapters.viewHolders.ViewHolderDonante;
import com.example.almacenamiento_android.clases.Donante;

import java.util.ArrayList;
import java.util.List;

public class AdapterDonante extends RecyclerView.Adapter<ViewHolderDonante> implements Filterable {

    private List<Donante> item;
    private List<Donante> itemFilterable;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener { void onItemClick(Donante item, int accion); }


    public AdapterDonante(List<Donante> item , OnItemClickListener itemClickListener) {
        this.item = item;
        this.itemFilterable = item;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderDonante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_donante, parent,false);
        return new ViewHolderDonante(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDonante holder, int position) {
        holder.bind(item.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Donante> filtered = new ArrayList<>();
//                 if (constraint != null && constraint.length() > 0) {
                String query = constraint.toString();
                if (query.isEmpty()) {
                    filtered = itemFilterable;
                } else {
                    for (Donante user : itemFilterable) {
                        if ( user.getNombreDonante().toUpperCase().contains(query.toUpperCase()) ||
                                user.getApellidoDonante().toUpperCase().contains(query.toUpperCase()) ||
                                String.valueOf(user.getIdDonante()).contains(query)

                        ) {

                            filtered.add(user);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                item = (ArrayList<Donante>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
