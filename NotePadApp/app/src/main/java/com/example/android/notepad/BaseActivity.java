package com.example.android.notepad;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.flyjingfish.titlebarlib.TitleBar;

public class BaseActivity extends AppCompatActivity {
    protected TitleBar titleBar;
    protected NotePadProvider notePadProvider;
    protected SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
        notePadProvider =new NotePadProvider(this);
        db= notePadProvider.getWritableDatabase();
    }
    public void initTitle(){
        titleBar = new TitleBar(this);
        titleBar.setShadow(1, Color.parseColor("#40454545"), TitleBar.ShadowType.GRADIENT);
        titleBar.setTitleGravity(TitleBar.TitleGravity.CENTER);
        titleBar.setOnBackViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setAboveContent(true);
        titleBar.attachToWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        notePadProvider.close();
    }
}
