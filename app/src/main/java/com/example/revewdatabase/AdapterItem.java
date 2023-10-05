package com.example.revewdatabase;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewProduct> {


    ArrayList<Item> items;
    Click click;

    public AdapterItem(ArrayList<Item> items,Click click) {
        this.items = items;
        this.click=click;
    }


    @NonNull
    @Override
    public ViewProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewProduct(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProduct holder, int position) {
        Item item=items.get(position);
        holder.name.setText(item.getName());
        holder.category.setText(item.getCategory());
        holder.price.setText(item.getPrice());
        holder.imageView.setImageURI(Uri.parse(item.getImage()));
        holder.edit.setOnClickListener(v -> click.onEdit(position,item.getName()));
        holder.delete.setOnClickListener(v -> click.onDelete(position,item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewProduct extends RecyclerView.ViewHolder {
        TextView name,category,price;
        ImageView edit,delete,imageView;
        public ViewProduct(@NonNull View view) {
            super(view);
            name=view.findViewById(R.id.nameProduct);
            category=view.findViewById(R.id.categoryProduct);
            price=view.findViewById(R.id.priceProduct);
            edit=view.findViewById(R.id.editProduct);
            delete=view.findViewById(R.id.deleteProduct);
            imageView=view.findViewById(R.id.imageProductView);
        }
    }
}
