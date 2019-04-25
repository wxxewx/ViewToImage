package com.xiaofeiluo.viewtoimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.xiaofeiluo.viewtoimage.call.IBitMapCallBack;
import com.xiaofeiluo.viewtoimage.call.IViewReadyCallBack;

import java.util.ArrayList;

public class ScreenshotsHelper {


    private ImageBuild imageBuild;


    private ArrayList<Task> tasks = new ArrayList<>();

    private boolean isDoing = false;

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
     * @return
     */
    private Bitmap drawImage(View view) {
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
    public void generateImages(final AppCompatActivity activity, final View view, final IBitMapCallBack iBitMapCallBack, ImageBuild imageBuild) {
        addInTasks(activity, view, iBitMapCallBack);
        if (!isDoing) {
            doTask();
        }
    }


    private void doTask() {
        isDoing = true;
        if (tasks.size() == 0) {
            isDoing = false;
        } else {
            final Task task = tasks.get(0);
            if (task.activity == null) {
                tasks.remove(task);
                isDoing = false;
                return;
            }
            final FragmentManager supportFragmentManager = task.activity.getSupportFragmentManager();
            task.view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            final StubFragment stubFragment = createStubFragment(task.activity, task.view);
            stubFragment.setViewReadyCallBack(new IViewReadyCallBack() {

                @Override
                public void viewReady() {
                    Bitmap bitmap = drawImage(task.view);
                    task.iBitMapCallBack.getBitMap(bitmap);
                    supportFragmentManager.beginTransaction().remove(stubFragment).commit();
                    isDoing = false;
                }
            });
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, stubFragment).commit();
        }

    }


    private void addInTasks(AppCompatActivity activity, View view, IBitMapCallBack iBitMapCallBack) {
        Task task = new Task();
        task.setActivity(activity);
        task.setiBitMapCallBack(iBitMapCallBack);
        task.setView(view);
        task.setImageBuild(imageBuild);
        tasks.add(task);
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
