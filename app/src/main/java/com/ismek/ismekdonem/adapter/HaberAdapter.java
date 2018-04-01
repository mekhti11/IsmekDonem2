package com.ismek.ismekdonem.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ismek.ismekdonem.R;
import com.ismek.ismekdonem.entity.Haber;
import com.ismek.ismekdonem.listener.HaberListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.HaberViewHolder>{

    private List<Haber> haberList;
    private Context context;
    private HaberListener listener;

    public HaberAdapter(Context cntx,List<Haber> haberList,HaberListener haberListener) {
        this.haberList = haberList;
        this.context = cntx;
        this.listener = haberListener;
    }

    @Override
    public HaberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_haber,parent,false);
        return new HaberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HaberViewHolder holder, int position) {
        Haber haber = haberList.get(position);
        holder.txtTitle.setText(haber.getTitle());
        holder.txtSummary.setText(haber.getSummary());
        holder.txtAuthor.setText(haber.getAuthor());
        holder.txtPublished.setText(haber.getPublished());
        Picasso.with(context).load(haber.getImageUrl()).resize(200, 200).centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return haberList.size();
    }

    public class HaberViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView txtTitle;
        public TextView txtSummary;
        public TextView txtAuthor;
        public TextView txtPublished;
        public CardView cardView;

        public HaberViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtSummary = (TextView) itemView.findViewById(R.id.txtSummary);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
            txtPublished = (TextView) itemView.findViewById(R.id.txtPublished);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDetail(itemView,getAdapterPosition());
                }
            });
        }
    }

}
