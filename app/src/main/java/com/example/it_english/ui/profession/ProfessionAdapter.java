package com.example.it_english.ui.profession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_english.R;
import com.example.it_english.ui.terms.Term;
import com.example.it_english.ui.terms.TermAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfessionAdapter extends RecyclerView.Adapter<ProfessionAdapter.ViewHolder>{

    public interface OnProfessionClickListener{

        void onProfessionClick(Profession profession, int position);
    }

    private final ProfessionAdapter.OnProfessionClickListener onClickListener;


    private final LayoutInflater inflater;
    private final List<Profession> professions;

    ProfessionAdapter(Context context, List<Profession> professions, ProfessionAdapter.OnProfessionClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.professions = professions;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProfessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template, parent, false);
        return new ProfessionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfessionAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Profession profession = professions.get(position);

        holder.Name.setText(profession.getName());
        holder.Icon.setImageBitmap(profession.getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onProfessionClick(profession, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return professions.size();
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
