package edu.poly.delifood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import edu.poly.delifood.Activity.DetailsActivity;
import edu.poly.delifood.Interface.ItemClickListener;
import edu.poly.delifood.R;
import edu.poly.delifood.model.NewProduct;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.MyViewHolder> {
    Context context;
    List<NewProduct> array;

    public NewProductAdapter(Context context, List<NewProduct> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NewProduct newProduct = array.get(position);
        holder.txtNewName.setText(newProduct.getNewName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText("Giá: " + decimalFormat.format(Double.parseDouble(newProduct.getNewPrice())) + "đ");
        Glide.with(context).load(newProduct.getNewImage()).into(holder.imvIgame);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    //click
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("chitiet", newProduct);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNewName, txtPrice;
        ImageView imvIgame;
        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtNewName = itemView.findViewById(R.id.txtNewName);
            imvIgame = itemView.findViewById(R.id.imvImage);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
