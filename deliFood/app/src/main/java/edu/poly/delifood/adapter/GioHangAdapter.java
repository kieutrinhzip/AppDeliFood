package edu.poly.delifood.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.IInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import edu.poly.delifood.Interface.ImageClickListener;
import edu.poly.delifood.R;
import edu.poly.delifood.model.EventBus.TinhTongEvent;
import edu.poly.delifood.model.GioHang;
import edu.poly.delifood.untils.Untils;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {

    Context context;
    List<GioHang> gioHangList;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        GioHang gioHang = gioHangList.get(position);
        holder.item_giohang_tensp.setText(gioHang.getTensp());
        holder.item_giohang_soluong.setText(gioHang.getSoluong() + " ");
        Glide.with(context).load(gioHang.getHinhanh()).into(holder.item_giohang_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohang_gia.setText(decimalFormat.format((gioHang.getGiasp())));
        long gia = gioHang.getSoluong() * gioHang.getGiasp();
        holder.item_giohang_giasp2.setText(decimalFormat.format(gia) + "đ"); //trên sô lượng
        holder.setListenner(new ImageClickListener() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri == 1){
                    if(gioHangList.get(pos).getSoluong() > 1){
                        int soluongmoi = gioHangList.get(pos).getSoluong()-1;
                        gioHangList.get(pos).setSoluong(soluongmoi);

                        holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong() + " ");
                        long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                        holder.item_giohang_giasp2.setText(decimalFormat.format(gia) + "đ");
                        EventBus.getDefault().postSticky(new TinhTongEvent());

                    }else if (gioHangList.get(pos).getSoluong() ==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Xóa sản phẩm khỏi giỏ hàng?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Untils.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());      
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }
                }else if(giatri == 2){
                    if(gioHangList.get(pos).getSoluong() < 10){
                        int soluongmoi = gioHangList.get(pos).getSoluong()+1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                    }
                    holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong() + " ");
                    long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                    holder.item_giohang_giasp2.setText(decimalFormat.format(gia) + "đ");
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, imgtru, imgcong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        ImageClickListener listenner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);

            //click plus
            imgcong.setOnClickListener(this);
            imgtru.setOnClickListener(this);
        }

        public void setListenner(ImageClickListener listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {
            if(view == imgtru){
                listenner.onImageClick(view, getAdapterPosition(), 1);
            }else if(view == imgcong){
                listenner.onImageClick(view, getAdapterPosition(), 2);
            }
        }
    }
}
