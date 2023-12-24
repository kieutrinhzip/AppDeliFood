package edu.poly.delifood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rc;
    EditText edtsearch;
    DoanAdapter adapterDoan;
    List<NewProduct> sanPhamMoiList;
    API api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        ActionToolBar();
    }

    private void initView() {
        sanPhamMoiList = new ArrayList<>();
        api = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);
        edtsearch = findViewById(R.id.edtsearch);
        toolbar = findViewById(R.id.toolbarsearch);
        rc = findViewById(R.id.rc_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(layoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    sanPhamMoiList.clear();
                    adapterDoan = new DoanAdapter(getApplicationContext(), sanPhamMoiList);
                    rc.setAdapter(adapterDoan);
                }else {
                    getDataSearch(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getDataSearch(String s) {
        sanPhamMoiList.clear();
        compositeDisposable.add(api.search(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        newProductModel -> {
                            if(newProductModel.isSuccess()){
                                sanPhamMoiList = newProductModel.getResult();
                                adapterDoan = new DoanAdapter(getApplicationContext(), sanPhamMoiList);
                                rc.setAdapter(adapterDoan);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
}