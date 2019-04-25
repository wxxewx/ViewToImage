package com.xiaofeiluo.viewtoimagedemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

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

                Bitmap bitmap = loadBitmapFromView(goods_layout);
                image.setImageBitmap(bitmap);
            }
        });

    }


    private Bitmap loadBitmapFromView(View v) {
        int w = v.getMeasuredWidth();
        int h = v.getMeasuredHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }

    private Bitmap loadBitmapFromView2(View v) {
        int w = v.getMeasuredWidth();
        int h = v.getMeasuredHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }
}


