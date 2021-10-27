package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Credits;

import java.util.List;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CardViewHolder> {

    private Context context;
    private List<Credits.Cast> castList;
    private List<Credits.Cast> getCastList(){
        return castList;
    }
    public void setCastList(List<Credits.Cast> castList){
        this.castList = castList;
    }
    public CreditAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_details,parent,false);
    return new CreditAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final Credits.Cast result = getCastList().get(position);
        String image = result.getProfile_path();
        String name = result.getName();
        String order = String.valueOf(result.getOrder());
        if(image != null){
            Glide.with(context).load(Const.IMG_URL + image).into(holder.img_cast);
            holder.name_cast.setText(name);
            holder.order_cast.setText(order);
            holder.img_cast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,name ,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Glide.with(context).load(Const.IMG_URL + image).into(holder.img_cast);
            holder.name_cast.setText(name);
            holder.order_cast.setText(order);
        }

    }

    @Override
    public int getItemCount() {
        return getCastList().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
         ImageView img_cast;
         TextView name_cast, order_cast;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            img_cast = itemView.findViewById(R.id.img_cast);
            name_cast = itemView.findViewById(R.id.name_cast);
            order_cast = itemView.findViewById(R.id.order_cast);
        }
    }
}
