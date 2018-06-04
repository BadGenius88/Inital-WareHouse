package com.kthdv.adviserapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kthdv.adviserapp.presenter.BasePresenter;

/**
 * Created by Thaihn on 11/24/2017.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private T presenter;

    protected abstract int getLayoutResources();

    protected abstract void initVariables(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract T initPresenter();

    protected T getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResources());
        this.presenter = initPresenter();

        initVariables(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.onViewDestroy();
        }
    }
}
