package edu.poly.delifood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.poly.delifood.R;
import edu.poly.delifood.adapter.DonHangAdapter;
import edu.poly.delifood.retrofit.API;
import edu.poly.delifood.retrofit.RetrofitClient;
import edu.poly.delifood.untils.Untils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemDonHangActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    API api;
    RecyclerView redonhang;
    Toolbar toolbar;
    TextView txtDeliving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don_hang);

        initView();
        initToolbar();
        getOder();



        api = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);
        compositeDisposable.add(api.xemDonHang(Untils.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donHangModel -> {
                            DonHangAdapter adapter = new DonHangAdapter(getApplicationContext(), donHangModel.getResult());
                            redonhang.setAdapter(adapter);

                        },
                        throwable -> {

                        }
                )
        );
    }

    private void getOder() {
    }

    private void initToolbar() {
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

    private void initView() {
        txtDeliving = findViewById(R.id.txtDeliving);
        api = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);
        redonhang = findViewById(R.id.recycleview_donhang);
        toolbar = findViewById(R.id.toolbar);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        redonhang.setLayoutManager(layoutManager);

        txtDeliving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VanchuyenActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}