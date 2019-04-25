package com.xiaofeiluo.viewtoimage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Objects;

public class StubFragment extends Fragment {


    private View view;
    private IViewReadyCallBack iViewReadyCallBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view.post(new Runnable() {
            @Override
            public void run() {
                if (iViewReadyCallBack != null) {
                    iViewReadyCallBack.viewReady();
                }
            }
        });
        view.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setViewReadyCallBack(IViewReadyCallBack iViewReadyCallBack) {
        this.iViewReadyCallBack = iViewReadyCallBack;
    }
}
