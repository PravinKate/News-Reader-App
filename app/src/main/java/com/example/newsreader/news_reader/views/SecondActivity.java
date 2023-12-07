package com.example.newsreader.news_reader.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsreader.R;
import com.example.newsreader.news_reader.models.News;

public class SecondActivity extends AppCompatActivity {
    private ImageView newsImageView;
    private TextView contentTextView;
    private TextView urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        newsImageView = findViewById(R.id.news_image_view);
        contentTextView = findViewById(R.id.content_text_view);
        urlTextView = findViewById(R.id.url_text_view);

        // Get news data from Intent
        News news = getIntent().getParcelableExtra("news");

        // TODO: Populate views with detailed news content
        // Picasso.get().load(news.getImageUrl()).into(newsImageView);
        contentTextView.setText(news.getDescription());
        urlTextView.setText(news.getUrl());
    }
}