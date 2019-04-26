package com.xiaofeiluo.viewtoimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xiaofeiluo.viewtoimage.call.IBitMapCallBack;
import com.xiaofeiluo.viewtoimage.call.IViewReadyCallBack;

import java.util.ArrayList;

public class ScreenshotsHelper {


    private ImageBuild imageBuild;


    public ScreenshotsHelper() {
        initBuild();
    }


    /**
     * 初始化图片相关的配置
     */
    private void initBuild() {
        ImageBuild imageBuild = new ImageBuild();
        imageBuild.setBackgroud(Color.WHITE);//默认背景色是白色
        this.imageBuild = imageBuild;
    }


    /**
     * 绘制图片
     *
     * @param view
     * @param imageBuild
     * @return
     */
    private Bitmap drawImage(View view, ImageBuild imageBuild) {
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(imageBuild.getBackgroud());
        /** 如果不设置canvas画布为白色，则生成透明 */
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return bitmap;
    }


    /**
     * 生成图片
     *
     * @param activity
     * @param view
     * @param iBitMapCallBack
     */
    public void generateImages(final AppCompatActivity activity, final View view, final IBitMapCallBack iBitMapCallBack) {
        generateImages(activity, view, iBitMapCallBack, imageBuild);
    }

    /**
     * 生成图片
     *
     * @param activity
     * @param view
     * @param iBitMapCallBack
     */
    public void generateImages(final AppCompatActivity activity, final View view, final IBitMapCallBack iBitMapCallBack, final ImageBuild imageBuild) {
        final FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        final StubFragment stubFragment = createStubFragment(activity, view);
        stubFragment.setViewReadyCallBack(new IViewReadyCallBack() {

            @Override
            public void viewReady() {
                Bitmap bitmap = drawImage(view, imageBuild);
                iBitMapCallBack.getBitMap(bitmap);
                supportFragmentManager.beginTransaction().remove(stubFragment).commit();
                Log.e("ScreenshotsHelper", "finish the fragment");
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


    public ImageBuild getImageBuild() {
        return imageBuild;
    }


    public void setImageBuild(ImageBuild imageBuild) {
        this.imageBuild = imageBuild;
    }
}
