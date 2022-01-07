package com.example.it_english.ui.terms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_english.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TermAdapter  extends RecyclerView.Adapter<TermAdapter.ViewHolder>{

    public interface OnTermClickListener{

        void onTermClick(Term term, int position);
    }

    private final OnTermClickListener onClickListener;


    private final LayoutInflater inflater;
    private final List<Term> terms;

    TermAdapter(Context context, List<Term> terms, OnTermClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.terms = terms;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TermAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TermAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Term term = terms.get(position);

        holder.Name.setText(term.getName());

        holder.Icon.setImageBitmap(term.getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onTermClick(term, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return terms.size();
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
