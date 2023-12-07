package com.example.newsreader.news_reader.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsreader.R;
import com.example.newsreader.news_reader.models.News;
import com.example.newsreader.news_reader.views.SecondActivity;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsAdapter(List<News> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);

        // Bind data to ViewHolder views
        holder.titleTextView.setText(news.getTitle());
        holder.descriptionTextView.setText(news.getDescription());
        holder.publishedDateTextView.setText("Published at: " + news.getPublishedAt());
        // Load image using Glide
        Glide.with(context)
                .load(news.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
                .into(holder.imageView);

        // Handle click events
        holder.itemView.setOnClickListener(view -> {
            // Open second screen with detailed news content
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("news", news);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView publishedDateTextView;
        TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.news_image);
            titleTextView = itemView.findViewById(R.id.news_title);
            publishedDateTextView = itemView.findViewById(R.id.news_date);
            descriptionTextView = itemView.findViewById(R.id.news_description);
        }
    }
}

