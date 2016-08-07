package com.timen4.ronnny.customscrollview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
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
        CustomScrollView customScrollView = (CustomScrollView) findViewById(R.id.csv);
        WindowManager wm= (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        int mScreenHeight = wm.getDefaultDisplay().getHeight();

        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mScreenHeight);
        ViewGroup.LayoutParams layoutParams2=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mScreenHeight);
        ViewGroup.LayoutParams layoutParams3=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,mScreenHeight);

        ImageView imageView = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        ImageView imageView3 = new ImageView(this);

        imageView.setLayoutParams(layoutParams);
        imageView2.setLayoutParams(layoutParams2);
        imageView3.setLayoutParams(layoutParams3);

        imageView.setBackgroundResource(R.drawable.img1);
        imageView2.setBackgroundResource(R.drawable.img2);
        imageView3.setBackgroundResource(R.drawable.img3);

        customScrollView.addView(imageView);
        customScrollView.addView(imageView2);
        customScrollView.addView(imageView3);

    }
}
