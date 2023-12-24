package edu.poly.delifood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import edu.poly.delifood.R;

public class VanchuyenActivity extends AppCompatActivity {
    Toolbar toolbarvc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vanchuyen);

        initView();
        initToolbar();

    }

    private void initView() {
        toolbarvc = findViewById(R.id.toobarvc);


    }

    private void initToolbar() {
        setSupportActionBar(toolbarvc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //back
        toolbarvc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}