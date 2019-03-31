package com.android.retrofitdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.model.RetroData;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<RetroData> retroDataList;
    private Context context;

    public CustomAdapter(List<RetroData> retroDataList, Context context) {
        this.retroDataList = retroDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.itemview,viewGroup, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvTitle.setText(retroDataList.get(i).getTitle());
        viewHolder.tvAuthor.setText(retroDataList.get(i).getAuthor());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));

        builder.build().load(retroDataList.get(i).getUrl())
                .placeholder(R.drawable.thumb)
                .error(R.drawable.thumb)
                .into(viewHolder.imgAuthor);
    }

    @Override
    public int getItemCount() {
        return retroDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgAuthor;
        TextView tvAuthor, tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAuthor = itemView.findViewById(R.id.imgAuthor);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
