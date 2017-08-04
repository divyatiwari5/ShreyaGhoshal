package com.example.divya.sgg;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ImageView;

import static com.example.divya.sgg.ImageAdapter.mThumbids;
import static com.example.divya.sgg.ImageAdapter.position;

public class FullImage extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);



      Intent i = getIntent();
        int position = i.getExtras().getInt("mThumbids");
        ImageAdapter imageAdapter = new ImageAdapter(this);


        ImageView imageView = (ImageView) findViewById(R.id.fullview);
        imageView.setImageResource(imageAdapter.mThumbids[position]);





    }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            // TODO Auto-generated method stub
            super.onKeyDown(keyCode, event);
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    finish();
                    break;
            }
            return super.onKeyDown(keyCode, event);
        }



}







