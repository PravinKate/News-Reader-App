package com.example.newsreader.news_reader.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newsreader.R;
import com.example.newsreader.news_reader.models.News;

public class SecondActivity extends AppCompatActivity {
    private ImageView newsImageView;
    private TextView descriptionTextView;
    private TextView contentTextView;
    private TextView urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        newsImageView = findViewById(R.id.news_image_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        contentTextView = findViewById(R.id.content_text_view);
        urlTextView = findViewById(R.id.url_text_view);

        // Get news data from Intent
        News news = getIntent().getParcelableExtra("news");

        Glide.with(this)
                .load(news.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
                .into(newsImageView);
        descriptionTextView.setText(news.getDescription());
        contentTextView.setText(news.getContent());
        urlTextView.setText(news.getUrl());
    }
}