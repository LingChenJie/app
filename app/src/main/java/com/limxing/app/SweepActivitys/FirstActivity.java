package com.limxing.app.SweepActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.limxing.app.R;
import com.limxing.library.NoTitleBar.SystemBarTintManager;
import com.limxing.library.PullToRefresh.SwipeRefreshLayout;
import com.limxing.library.SwipeBack.SwipeBackActivity;

/**
 * Created by limxing on 16/2/16.
 */
public class FirstActivity extends SwipeBackActivity implements SwipeRefreshLayout.OnRefreshListener,
        SwipeRefreshLayout.OnLoadListener {
    private ListView main_listview;
    private SwipeRefreshLayout main_fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        SystemBarTintManager.initSystemBar(this, R.color.transparent);
        main_fresh = (com.limxing.library.PullToRefresh.SwipeRefreshLayout) findViewById(R.id.main_fresh);
        main_listview = (ListView) findViewById(R.id.main_listview);
        main_listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView textView = new TextView(FirstActivity.this);
                textView.setText("你是谁");
                textView.setHeight(200);
                return textView;
            }
        });
        main_fresh.setOnRefreshListener(this);
        main_fresh.setOnLoadListener(this);
    }

    public void next(View view) {
        Intent intent = new Intent(FirstActivity.this, FTwoActivity.class);
        startActivity(intent);

    }

    @Override
    public void onLoad() {
        new Handler().postDelayed(new Runnable() {

            public void run() {

                //execute the task
                main_fresh.setLoading(false);

            }

        }, 3000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {

            public void run() {

                //execute the task
                main_fresh.setRefreshing(false);

            }

        }, 3000);
    }
}