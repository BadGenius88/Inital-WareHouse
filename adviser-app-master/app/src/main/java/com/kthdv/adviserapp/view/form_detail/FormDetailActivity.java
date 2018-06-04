package com.kthdv.adviserapp.view.form_detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.Constants;
import com.kthdv.adviserapp.common.LoadingDialog;
import com.kthdv.adviserapp.common.recycler_view.FormDetailAdapter;
import com.kthdv.adviserapp.models.TrainingPointForm;
import com.kthdv.adviserapp.presenter.form_detail.FormDetailPresenter;
import com.kthdv.adviserapp.presenter.form_detail.FormDetailPresenterImpl;
import com.kthdv.adviserapp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormDetailActivity extends BaseActivity<FormDetailPresenter> implements FormDetailView, View.OnClickListener {
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.txt_student_name)
    TextView txtStudentName;
    @BindView(R.id.txt_student_id)
    TextView txtStudentID;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ln_button)
    LinearLayout lnButton;

    private FormDetailAdapter formDetailAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_form_detail;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        loadingDialog = new LoadingDialog(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.form_detail);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }

        formDetailAdapter = new FormDetailAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(formDetailAdapter);

        findViewById(R.id.btn_accept).setOnClickListener(this);
        findViewById(R.id.btn_reject).setOnClickListener(this);

        String id = getIntent().getStringExtra(Constants.KEY_ID);
        if(id == null) {
            finish();
        }
        getPresenter().fetchFormDetail(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;

            default:{
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected FormDetailPresenter initPresenter() {
        return new FormDetailPresenterImpl(this,this);
    }

    @Override
    public void showLoadingProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void showStudentForm(TrainingPointForm trainingPointForm) {
        txtStudentName.setText(trainingPointForm.getStudentName());
        txtStudentID.setText(trainingPointForm.getStudentUsername());
        formDetailAdapter.setData(trainingPointForm.getData());
    }

    @Override
    public void hideButtons() {
        lnButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_accept:{
                getPresenter().sendFeedback(true);
            }
            break;

            case R.id.btn_reject:{
                getPresenter().sendFeedback(false);
            }
            break;

            default:{
                break;
            }
        }
    }
}
