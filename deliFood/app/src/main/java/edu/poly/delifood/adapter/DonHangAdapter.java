package edu.poly.delifood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import edu.poly.delifood.R;
import edu.poly.delifood.model.DonHang;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listdonhang;

    public DonHangAdapter(Context context, List<DonHang> listdonhang) {
        this.context = context;
        this.listdonhang = listdonhang;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = listdonhang.get(position);
        holder.txtdonhang.setText("Mã đơn hàng: " + donHang.getId() + "xHnkt");
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.reChiTiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(donHang.getItem().size());
        // adapter chi tiet
        ChitietAdapter chitietAdapter = new ChitietAdapter(context ,donHang.getItem());
        holder.reChiTiet.setLayoutManager(layoutManager);
        holder.reChiTiet.setAdapter(chitietAdapter);
        holder.reChiTiet.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtdonhang;
        RecyclerView reChiTiet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            reChiTiet = itemView.findViewById(R.id.recycleview_chitiet);
        }
    }
}
