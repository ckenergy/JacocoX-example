package com.ckenergy.jacocox;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ckenergy.lib.MyClass;
import com.ckenergy.mylibrary.LibClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("main", "onCreate");
        setContentView(R.layout.activity_main);
        WatchService.open(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<String> list = new ArrayList<>();
            list.add("position 1");
            list.add("position 2");
            list.add("position 3");
            doSome(1L, 3.0, 5, "doSome", 2f, false, list, "ss", 1);
        }

//        LibClass.INSTANCE.show();
//        MyClass.INSTANCE.show();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                doSome1();
                Log.e("main", "doSome time:" + (System.currentTimeMillis() - start));
            }
        });
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void doSome(long l, double d, int type, String name, float f, boolean su, List<String> list, Object... args) {
        Log.e("main", "doSome");
    }

    private void doSome1() {
//        sendBroadcast(new Intent("jacocox.intent.action.generate"));
//        JacocoHelper.INSTANCE.generateEcFile(true, this);
        startActivity(new Intent(this, MainActivity2.class));
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}