package com.kthdv.adviserapp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.Constants;
import com.kthdv.adviserapp.common.recycler_view.StudentFormAdapter;
import com.kthdv.adviserapp.common.recycler_view.base.RecyclerViewAdapter;
import com.kthdv.adviserapp.models.StudentForm;
import com.kthdv.adviserapp.presenter.main.MainPresenter;
import com.kthdv.adviserapp.presenter.main.MainPresenterImpl;
import com.kthdv.adviserapp.view.BaseActivity;
import com.kthdv.adviserapp.view.form_detail.FormDetailActivity;
import com.kthdv.adviserapp.view.form_detail.FormDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, RecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.txt_no_content)
    TextView txtNoContent;

    private StudentFormAdapter studentFormAdapter;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.received_forms);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryLight, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        studentFormAdapter = new StudentFormAdapter(this);
        studentFormAdapter.addOnItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(studentFormAdapter);

        getPresenter().fetchReceivedStudentForms();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenterImpl(this, this);
    }

    @Override
    public void showRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshReceivedStudentForms(List<StudentForm> studentForms) {
        studentFormAdapter.refresh(studentForms);
        if(studentFormAdapter.getItemCount() == 0) {
            txtNoContent.setVisibility(View.VISIBLE);
        } else {
            txtNoContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        Intent intent = new Intent(this, FormDetailActivity.class);
        intent.putExtra(Constants.KEY_ID, studentFormAdapter.getItem(position, StudentForm.class).getStudentID());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getPresenter().fetchReceivedStudentForms();
    }
}
