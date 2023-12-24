package edu.poly.delifood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.poly.delifood.R;
import edu.poly.delifood.retrofit.API;
import edu.poly.delifood.retrofit.RetrofitClient;
import edu.poly.delifood.untils.Untils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKyActivity extends AppCompatActivity {
    EditText email, pass, repass, mobile, username;
    AppCompatButton button;
    TextView txthavetk;
    API api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        initView();
        initControl();
    }

    private void initControl() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKi();
            }
        });
        txthavetk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void dangKi() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        String str_user = username.getText().toString().trim();

        if (TextUtils.isEmpty(str_email)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_pass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Pass", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập RePass", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_mobile)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Mobile", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_user)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập UserName", Toast.LENGTH_SHORT).show();
        } else {
            if (str_pass.equals(str_repass)) {
                // post data
                compositeDisposable.add(api.dangky(str_email, str_pass, str_user, str_mobile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if (userModel.isSuccess()) {
                                        Untils.user_current.setEmail(str_email);
                                        Untils.user_current.setPass(str_pass);
                                        Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));

            } else {
                Toast.makeText(getApplicationContext(), "Pass bạn nhập chưa trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        api = RetrofitClient.getInstance(Untils.BASE_URL).create(API.class);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        mobile = findViewById(R.id.mobile);
        username = findViewById(R.id.username);
        button = findViewById(R.id.btndangky);
        txthavetk = findViewById(R.id.txthavetk);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}