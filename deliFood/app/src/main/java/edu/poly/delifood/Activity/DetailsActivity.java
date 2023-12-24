package edu.poly.delifood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

import edu.poly.delifood.R;
import edu.poly.delifood.model.GioHang;
import edu.poly.delifood.model.NewProduct;
import edu.poly.delifood.untils.Untils;

public class DetailsActivity extends AppCompatActivity {
    TextView tensp, giasp, mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    NewProduct newProduct;
    NotificationBadge badge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        ActionToolBar();
        initData();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themGioHang();
            }
        });
    }

    private void themGioHang() {
        if(Untils.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i = 0; i < Untils.manggiohang.size(); i++){
                if(Untils.manggiohang.get(i).getId() == newProduct.getId()){
                    Untils.manggiohang.get(i).setSoluong(soluong + Untils.manggiohang.get(i).getSoluong());
                    long gia = Long.parseLong(newProduct.getNewPrice()) * Untils.manggiohang.get(i).getSoluong();
                    Untils.manggiohang.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if(flag == false){
                long gia = Long.parseLong(newProduct.getNewPrice()) * soluong;
                GioHang gioHang = new GioHang();
                gioHang.setGiasp(gia);
                gioHang.setSoluong(soluong);
                gioHang.setId(newProduct.getId());
                gioHang.setTensp(newProduct.getNewName());
                gioHang.setHinhanh(newProduct.getNewImage());
                Untils.manggiohang.add(gioHang);
            }
        }else{
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(newProduct.getNewPrice()) * soluong;
            GioHang gioHang = new GioHang();
            gioHang.setGiasp(gia);
            gioHang.setSoluong(soluong);
            gioHang.setId(newProduct.getId());
            gioHang.setTensp(newProduct.getNewName());
            gioHang.setHinhanh(newProduct.getNewImage());
            Untils.manggiohang.add(gioHang);
        }
        int totalItem = 0;
        for(int i=0; i<Untils.manggiohang.size(); i++){
            totalItem = totalItem + Untils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
        newProduct = (NewProduct) getIntent().getSerializableExtra("chitiet");
        tensp.setText(newProduct.getNewName());
        mota.setText(newProduct.getNewDes());
        Glide.with(getApplicationContext()).load(newProduct.getNewImage()).into(imghinhanh);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(newProduct.getNewPrice())) + "Đ");
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapterspin);
    }

    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota = findViewById(R.id.txtmotachitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar = findViewById(R.id.toolbar);
        badge =  findViewById(R.id.menu_sl);
        FrameLayout frameLayoutgohang = findViewById(R.id.framegiohang);
        frameLayoutgohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(gohang);
            }
        });
        if (Untils.manggiohang != null) {
            int totalItem = 0;
            for(int i=0; i<Untils.manggiohang.size(); i++){
                totalItem = totalItem + Untils.manggiohang.get(i).getSoluong();
            }

            badge.setText(String.valueOf(totalItem));

        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for(int i=0; i<Untils.manggiohang.size(); i++){
            totalItem = totalItem + Untils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }
}