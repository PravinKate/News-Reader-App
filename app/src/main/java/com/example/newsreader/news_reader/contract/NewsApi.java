package com.example.newsreader.news_reader.contract;
import com.example.newsreader.news_reader.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("v2/top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("apiKey") String apiKey,
            @Query("sources") String sources,
            @Query("pageSize") int pageSize
    );
}
