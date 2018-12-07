package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecylcerView;
    private ProgressBar mProgressBar;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private ArrayList<NewsItem> mNewsList = new ArrayList<>();
    private NewsItemViewModel mNewsItemViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.news_progress);

        mRecylcerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, mNewsList);
        mRecylcerView.setAdapter(mNewsRecyclerViewAdapter);
        mRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        mNewsItemViewModel.loadAllNews().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mProgressBar.setVisibility(View.GONE);
                mNewsRecyclerViewAdapter.setNews(newsItems);

            }
        });
        ServiceScheduler.refreshJob(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_news, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        mProgressBar.setVisibility(View.VISIBLE);
        int itemThatWasClickedId = item.getItemId();
        if(itemThatWasClickedId == R.id.action_clear){
            mNewsItemViewModel.clearDatabase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
