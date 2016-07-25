package com.timen4.ronnny.customscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.timen4.ronnny.customscrollview.widget.CustomScrollView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainView = (RelativeLayout) findViewById(R.id.main);
        CustomScrollView customScrollView = new CustomScrollView(MainActivity.this);
        mainView.addView(customScrollView);

    }
}
