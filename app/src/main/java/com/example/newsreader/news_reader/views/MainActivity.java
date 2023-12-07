package com.example.newsreader.news_reader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.newsreader.BuildConfig;
import com.example.newsreader.R;
import com.example.newsreader.news_reader.adapters.NewsAdapter;
import com.example.newsreader.news_reader.contract.NewsApi;
import com.example.newsreader.news_reader.models.News;
import com.example.newsreader.news_reader.models.NewsResponse;
import com.example.newsreader.news_reader.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setShimmer(true);

        String apiKey = BuildConfig.API_KEY;
        String sources = "bbc-news";
        int pageSize = 100;

        NewsApi newsApi = RetrofitClient.getClient().create(NewsApi.class);
        Call<NewsResponse> call = newsApi.getTopHeadlines(apiKey, sources, pageSize);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsList = response.body().getArticles();
                    newsAdapter.setData(newsList);
                    newsAdapter.setShimmer(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load news", Toast.LENGTH_SHORT).show();
                newsAdapter.setShimmer(false);
            }
        });
    }
}