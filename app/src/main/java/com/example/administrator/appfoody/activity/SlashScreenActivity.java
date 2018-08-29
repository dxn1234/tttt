package com.example.administrator.appfoody.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.appfoody.R;

public class SlashScreenActivity extends AppCompatActivity {
    TextView txtphienban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);
        anhXa();
        try {
            PackageInfo packageInfo=getPackageManager().getPackageInfo(getPackageName(),0);
            txtphienban.setText("Phiên bản "+packageInfo.versionName);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent idangnhap=new Intent(SlashScreenActivity.this,DangNhapActivity.class);
                    startActivity(idangnhap);
                    finish();
                }
            },2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void anhXa() {
        txtphienban=findViewById(R.id.txt_phienban);
    }
}
