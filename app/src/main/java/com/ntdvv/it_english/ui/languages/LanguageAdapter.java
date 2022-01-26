package com.ntdvv.it_english.ui.languages;

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

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{

    public interface OnLanguageClickListener{
        void onLanguageClick(Language language, int position);
    }

    private final LanguageAdapter.OnLanguageClickListener onClickListener;


    private final LayoutInflater inflater;
    private final List<Language> languages;

    LanguageAdapter(Context context, List<Language> languages, LanguageAdapter.OnLanguageClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.languages = languages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template, parent, false);
        return new LanguageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Language language = languages.get(position);

        holder.Name.setText(language.getTitle());
        holder.Icon.setImageBitmap(language.getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onLanguageClick(language, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languages.size();
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
