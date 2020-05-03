package com.example.nativation.paging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nativation.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class PagingActivity extends AppCompatActivity implements OnLoadMoreListener, OnRefreshListener {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private PagingAdapter mAdapter;
    private PagingViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);



        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter  = new PagingAdapter();
        recyclerView.setAdapter(mAdapter);

        viewModel = new ViewModelProvider(this).get(PagingViewModel.class);
        viewModel.getArticleLiveData().observe(this, new Observer<PagedList<ArticleResponse.DataBean.DatasBean>>() {
            @Override
            public void onChanged(PagedList<ArticleResponse.DataBean.DatasBean> datasBeans) {
                mAdapter.submitList(datasBeans);
            }
        });
        viewModel.getBoundaryPageData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean haData) {
                if(!haData){
                    refreshLayout.finishLoadMore();
                    refreshLayout.finishRefresh();
                }
            }
        });
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        viewModel.getDataSource().invalidate();
    }
    }


