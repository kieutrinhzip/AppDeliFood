package edu.poly.delifood.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import edu.poly.delifood.R;
import edu.poly.delifood.adapter.GioHangAdapter;
import edu.poly.delifood.adapter.NewProductAdapter;
import edu.poly.delifood.adapter.productTypeAdapter;
import edu.poly.delifood.model.NewProduct;
import edu.poly.delifood.model.NewProductModel;
import edu.poly.delifood.model.productType;
import edu.poly.delifood.retrofit.API;
import edu.poly.delifood.retrofit.RetrofitClient;
import edu.poly.delifood.untils.Untils;
import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView rcMainMonitor;
    NavigationView navigationView;
    ListView lvMainMonitor;
    productTypeAdapter productTypeAdapter;

    List<productType> arrProductType;
    DrawerLayout drawerLayout;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    API api;
    List<NewProduct> arrNewProduct;
    NewProductAdapter newproductAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);
        Call();
        actionSlider();

        if(eConneted(this)){

            actionFlipper();
            getProductType();
            getNewproduct();
            getEventMenu();
        }else{
            Toast.makeText(getApplicationContext(), "Chưa kết nối internet!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEventMenu() {
        lvMainMonitor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case 1:
                        Intent doan = new Intent(getApplicationContext(), DoanActivity.class);
                        doan.putExtra("newType",1);
                        startActivity(doan);
                        break;
                    case 2:
                        Intent douong = new Intent(getApplicationContext(), DoanActivity.class);
                        douong.putExtra("newType",2);
                        startActivity(douong);
                        break;
                    case 3:
                        Intent dochay = new Intent(getApplicationContext(), DoanActivity.class);
                        dochay.putExtra("newType",3);
                        startActivity(dochay);
                        break;
                    case 4:
                        Intent banhkem = new Intent(getApplicationContext(), DoanActivity.class);
                        banhkem.putExtra("newType",4);
                        startActivity(banhkem);
                        break;
//                    case 5:
//                        Intent monlau = new Intent(getApplicationContext(), DoanActivity.class);
//                        monlau.putExtra("newType",5);
//                        startActivity(monlau);
//                        break;
//
//                    case 6:
//                        Intent sushi = new Intent(getApplicationContext(), DoanActivity.class);
//                        sushi.putExtra("newType",6);
//                        startActivity(sushi);
//                        break;
                    case 5:
                        Intent donhang = new Intent(getApplicationContext(), XemDonHangActivity.class);
                        startActivity(donhang);
                        break;
                    case 6:
                        // xoa key user
                        Paper.book().delete("user");
                        Intent dangxuat = new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangxuat);
                        finish();
                        break;
                }
            }
        });
    }

    private void getNewproduct() {
        compositeDisposable.add(api.getNewProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newProductModel -> {
                            if(newProductModel.isSuccess()){
                                arrNewProduct = newProductModel.getResult();
                                newproductAdapter = new NewProductAdapter(getApplicationContext(), arrNewProduct);
                                rcMainMonitor.setAdapter(newproductAdapter);

                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void getProductType() {
        compositeDisposable.add(api.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if(loaiSpModel.isSuccess()){
                                arrProductType = loaiSpModel.getResult();
                                productTypeAdapter = new productTypeAdapter(getApplicationContext(),arrProductType);
                                lvMainMonitor.setAdapter(productTypeAdapter);
                            }
                        }
                ));
    }


    private void actionFlipper() {
        List<String> arrQC = new ArrayList<>();
        arrQC.add("https://images.foody.vn/delivery/collection/s320x200/image-964a0084-221230173816.png");
        arrQC.add("https://images.foody.vn/delivery/collection/s320x200/image-9ca86bf6-211129213241.jpeg");
        arrQC.add("https://images.foody.vn/delivery/collection/s320x200/image-e12ce9e6-220406074410.png");
        arrQC.add("https://images.foody.vn/delivery/collection/s320x200/image-1387821b-221229145305.png");
        arrQC.add("https://images.foody.vn/delivery/collection/s320x200/image-6d1d20a4-230403171211.jpeg");
        for (int i=0; i<arrQC.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrQC.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slider_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_in_right);
        Animation slider_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slider_out_right);
        viewFlipper.setInAnimation(slider_in);
        viewFlipper.setOutAnimation(slider_out);
    }

    private void actionSlider() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Call() {
        imgsearch = findViewById(R.id.imgsearch);
        toolbar = findViewById(R.id.toolbarMainMonitor);
        viewFlipper = findViewById(R.id.viewplipper);
        rcMainMonitor = findViewById(R.id.recycleview);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcMainMonitor.setLayoutManager(layoutManager);
        rcMainMonitor.setHasFixedSize(true);

        lvMainMonitor = findViewById(R.id.lvMainMonitor);
        drawerLayout = findViewById(R.id.drawlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        //khoi tao list

        arrProductType = new ArrayList<>();
        arrNewProduct = new ArrayList<>();
        if(Untils.manggiohang == null){
            Untils.manggiohang = new ArrayList<>();
        }else{
            int totalItem = 0;
            for(int i=0; i<Untils.manggiohang.size(); i++){
                totalItem = totalItem + Untils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(gohang);
            }
        });

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
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

    private boolean eConneted (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
