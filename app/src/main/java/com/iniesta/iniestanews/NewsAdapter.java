package com.iniesta.iniestanews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsItem> newsItems;
    private Context context;
    private RecyclerViewClickListener mListener;

    public NewsAdapter(List<NewsItem> items, Context c, RecyclerViewClickListener listener) {
        newsItems = items;
        context = c;
        mListener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        NewsViewHolder holder = new NewsViewHolder(view, mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        final NewsItem item = newsItems.get(position);

        holder.titleTextView.setText(item.getTitle());

        if (item.getUrlToImage().equals("empty")) {
            holder.imageView.setImageResource(R.drawable.bottom_shadow);
        } else {
            Glide.with(context)
                    .load(item.getUrlToImage())
                    .listener(requestListener)
                    .apply(new RequestOptions()
                            .error(R.drawable.bottom_shadow))
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;
        protected TextView titleTextView;
        private RecyclerViewClickListener hListener;

        public NewsViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            titleTextView = itemView.findViewById(R.id.title);
            hListener = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            hListener.onClick(view, getAdapterPosition());
        }
    }

    private RequestListener<Drawable> requestListener = new RequestListener<Drawable>() {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            // todo log exception to central service or something like that
            System.out.println(e.getMessage());

            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            // everything worked out, so probably nothing to do
            return false;
        }
    };
}
