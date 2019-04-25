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
    private Button generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        final View goods_layout = View.inflate(this, R.layout.goods_layout, null);
        setContentView(view);
        image = findViewById(R.id.image);
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
            }
        });

    }


}


