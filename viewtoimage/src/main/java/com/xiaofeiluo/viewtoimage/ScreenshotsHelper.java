package com.xiaofeiluo.viewtoimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class ScreenshotsHelper {


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


    public void generateImages(final AppCompatActivity activity, final View view, final IBitMapCallBack iBitMapCallBack) {
        final FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        final StubFragment stubFragment = createStubFragment(activity, view);
        stubFragment.setViewReadyCallBack(new IViewReadyCallBack() {

            @Override
            public void viewReady() {
                Bitmap bitmap = loadBitmapFromView(view);
                iBitMapCallBack.getBitMap(bitmap);
                supportFragmentManager.beginTransaction().remove(stubFragment).commit();
            }
        });

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(android.R.id.content, stubFragment).commit();
    }


    private StubFragment createStubFragment(Activity activity, View view) {
        StubFragment stubFragment = new StubFragment();
        stubFragment.setView(view);
        return stubFragment;
    }
}
