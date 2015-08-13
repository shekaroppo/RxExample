package com.rx.network;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rx.BaseApplication;
import com.rx.R;
import com.rx.BaseActivity;
import com.rx.network.adapter.ExampleAdapter;
import com.rx.network.model.ExampleModel;
import com.rx.network.presenter.ExamplePresenter;
import com.rx.network.view.LceView;
import com.rx.dagger.module.ExampleModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sekhar on 4/6/15.
 */
public class RetrofitActivity extends BaseActivity implements LceView<List<ExampleModel>> {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    ProgressBar mLoadingView;
    @Bind(R.id.errorView)
    TextView mErrorView;
    @Inject
    ExamplePresenter mPresenter;
    @Inject
    ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main);
        ButterKnife.bind(this);
        BaseApplication.getComponent(this).plus(new ExampleModule(this)).inject(this);
        setUI();
        loadData();
    }

    private void loadData() {
        mPresenter.loadData();
    }

    private void setUI() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeFromDataStore();
    }

    @Override
    public void showProgress() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showContent(List<ExampleModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
    }
}
