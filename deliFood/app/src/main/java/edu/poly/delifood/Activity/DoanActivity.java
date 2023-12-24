package edu.poly.delifood.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.poly.delifood.R;
import edu.poly.delifood.adapter.DoanAdapter;
import edu.poly.delifood.model.NewProduct;
import edu.poly.delifood.retrofit.API;
import edu.poly.delifood.retrofit.RetrofitClient;
import edu.poly.delifood.untils.Untils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DoanActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    API apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    int newType;
    DoanAdapter adapterDoan;
    List<NewProduct> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doan);
        apiBanHang = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);
        newType = getIntent().getIntExtra("newType", 1);
        AnhXa();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanPhamMoiList.size()-1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapterDoan.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapterDoan.notifyItemRemoved(sanPhamMoiList.size());
                page = page + 1;
                getData(page);
                adapterDoan.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void getData(int page) {
        compositeDisposable.add(apiBanHang.getSanPham(page, newType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newProductModel -> {
                            if (newProductModel.isSuccess()){
                                if (adapterDoan == null){
                                    sanPhamMoiList = newProductModel.getResult();
                                    adapterDoan = new DoanAdapter(getApplicationContext(), sanPhamMoiList);
                                    recyclerView.setAdapter(adapterDoan);
                                } else {
                                    int vitri = sanPhamMoiList.size()-1;
                                    int soluongadd = newProductModel.getResult().size();
                                    for(int i = 0; i < soluongadd; i++){
                                        sanPhamMoiList.add(newProductModel.getResult().get(i));
                                    }
                                    adapterDoan.notifyItemRangeInserted(vitri, soluongadd);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Hết dữ liệu rồi", Toast.LENGTH_LONG).show();
                                isLoading = true;
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối server", Toast.LENGTH_LONG).show();
                        }
                ));
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

    private void AnhXa() {
        toolbar = findViewById(R.id.toobarDA);
        recyclerView = findViewById(R.id.rc_doan);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        sanPhamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}