package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;

import java.util.List;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.CardViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> productionlist;
    private List<Movies.ProductionCompanies> getProduction_companies(){
        return productionlist;
    }
    public void setProductionlist(List<Movies.ProductionCompanies>productionlist){
        this.productionlist = productionlist;
    }
    public ProductionAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ProductionAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_details,parent,false);
        return new ProductionAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final Movies.ProductionCompanies productionCompanies = getProduction_companies().get(position);

        String logo = productionCompanies.getLogo_path();
        String name = productionCompanies.getName();

        if(logo != null){
            Glide.with(context).load(Const.IMG_URL + productionCompanies.getLogo_path()).into(holder.img_production);
            holder.img_production.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,name ,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Glide.with(context).load(Const.IMG_URL + logo).into(holder.img_production);
        }

    }

    @Override
    public int getItemCount() {
        return getProduction_companies().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img_production;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            img_production = itemView.findViewById(R.id.img_production);
        }
    }
}
