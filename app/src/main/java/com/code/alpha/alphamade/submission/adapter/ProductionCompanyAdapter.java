package com.code.alpha.alphamade.submission.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code.alpha.alphamade.BuildConfig;
import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.model.ProductionCompany;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder> {
    private ArrayList<ProductionCompany> list;

    public ProductionCompanyAdapter(ArrayList<ProductionCompany> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductionCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        return new ProductionCompanyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView logo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            logo = itemView.findViewById(R.id.logo);
        }

        void bind(ProductionCompany item) {
            name.setText(item.getName());
            Picasso.get().load(BuildConfig.BASE_IMAGE_URL + item.getLogoPath()).into(logo);
        }
    }
}
