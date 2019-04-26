package com.xiaofeiluo.viewtoimagedemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaofeiluo.viewtoimage.call.IBitMapCallBack;
import com.xiaofeiluo.viewtoimage.ScreenshotsHelper;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private ImageView image9;
    private Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        final View goods_layout = View.inflate(this, R.layout.goods_layout, null);
        setContentView(view);
        image = findViewById(R.id.image);
        image = findViewById(R.id.image2);
        image = findViewById(R.id.image3);
        image = findViewById(R.id.image4);
        image = findViewById(R.id.image5);
        image = findViewById(R.id.image6);
        image = findViewById(R.id.image7);
        image = findViewById(R.id.image8);
        image = findViewById(R.id.image9);
        generate = findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image2.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image3.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image4.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image5.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image6.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image7.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image8.setImageBitmap(bitmap);
                    }
                });
                new ScreenshotsHelper().generateImages(MainActivity.this, goods_layout, new IBitMapCallBack() {
                    @Override
                    public void getBitMap(Bitmap bitmap) {
                        image9.setImageBitmap(bitmap);
                    }
                });
            }
        });

    }


}


