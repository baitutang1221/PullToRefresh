package com.jwenfeng.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.jwenfeng.pulltorefresh.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lstv;
    private PullToRefreshLayout pullToRefreshLayout;
    private List<String> list;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lstv = (ListView) findViewById(R.id.list);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.activity_list);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("PullToRefreshLayout"+i);
        }
        adapter = new ListAdapter(this,list);
        lstv.setAdapter(adapter);

        pullToRefreshLayout.autoRefresh();

        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            list.add("refresh"+i);
                        }
                        adapter.notifyDataSetChanged();
                        pullToRefreshLayout.finishRefresh();
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            list.add("loadmore"+i);
                        }
                        adapter.notifyDataSetChanged();
                        pullToRefreshLayout.finishLoadMore();
                    }
                },2000);
            }
        });


    }
}
