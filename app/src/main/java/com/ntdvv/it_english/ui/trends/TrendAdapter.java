package com.ntdvv.it_english.ui.trends;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntdvv.it_english.R;

import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.ViewHolder>{

    public interface OnTrendClickListener{

        void onTrendClick(Trend term, int position);
    }

    private final TrendAdapter.OnTrendClickListener onClickListener;


    private final LayoutInflater inflater;
    private final List<Trend> trends;

    TrendAdapter(Context context, List<Trend> trends, TrendAdapter.OnTrendClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.trends = trends;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TrendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template, parent, false);
        return new TrendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Trend trend = trends.get(position);

        holder.Name.setText(trend.getTitle());
        holder.Icon.setImageBitmap(trend.getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onTrendClick(trend, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView Name;
        final ImageView Icon;

        ViewHolder(View view){
            super(view);
            Name = view.findViewById(R.id.Name);
            Icon = view.findViewById(R.id.Icon);
        }
    }

}
