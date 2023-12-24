package edu.poly.delifood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class DoanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<NewProduct> array;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public DoanAdapter(Context context, List<NewProduct> array) {
        this.context = context;
        this.array = array;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doan, parent, false);
            return new MyViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            NewProduct product = array.get(position);
            myViewHolder.txt_tensp.setText(product.getNewName());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.txt_giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(product.getNewPrice())) + "đ");
            myViewHolder.txt_mota.setText(product.getNewDes());
            Glide.with(context).load(product.getNewImage()).into(myViewHolder.imv_img);
            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        //click
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("chitiet", product);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else{
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    //loading product
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_tensp, txt_giasp, txt_mota;
        ImageView imv_img;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tensp = itemView.findViewById(R.id.txt_tensp);
            txt_giasp = itemView.findViewById(R.id.txt_giasp);
            txt_mota = itemView.findViewById(R.id.txt_mota);
            imv_img = itemView.findViewById(R.id.imv_img);

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
