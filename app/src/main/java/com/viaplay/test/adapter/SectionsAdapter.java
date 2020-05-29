package com.viaplay.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viaplay.test.model.ViaplaySections;
import com.viaplay.test.R;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.SectionViewHolder> {

    private List<ViaplaySections> sections;
    private int rowLayout;
    private  Context context;
    private  OnItemClickListener mItemClickListener;

    public SectionsAdapter(List<ViaplaySections> sections, int rowLayout, Context context) {
        this.sections = sections;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public  class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout sectionsLayout;
        TextView sectionTitle;


        SectionViewHolder(View v) {
            super(v);
            sectionsLayout = v.findViewById(R.id.section_layout);
            sectionTitle = v.findViewById(R.id.section_tv);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }


    @Override
    public SectionsAdapter.SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, final int position) {

        holder.sectionTitle.setText(sections.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener=mItemClickListener;
    }

    public void  UpdateSectionList(List<ViaplaySections> sections) {
        this.sections = sections;
        notifyDataSetChanged();
    }


}
