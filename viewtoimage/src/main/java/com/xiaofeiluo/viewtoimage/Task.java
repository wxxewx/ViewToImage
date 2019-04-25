package com.xiaofeiluo.viewtoimage;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaofeiluo.viewtoimage.call.IBitMapCallBack;

public class Task {
    AppCompatActivity activity;
    View view;
    IBitMapCallBack iBitMapCallBack;
    ImageBuild imageBuild;

    public ImageBuild getImageBuild() {
        return imageBuild;
    }

    public void setImageBuild(ImageBuild imageBuild) {
        this.imageBuild = imageBuild;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public IBitMapCallBack getiBitMapCallBack() {
        return iBitMapCallBack;
    }

    public void setiBitMapCallBack(IBitMapCallBack iBitMapCallBack) {
        this.iBitMapCallBack = iBitMapCallBack;
    }
}
