package com.ismek.ismekdonem.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ismek.ismekdonem.R;
import com.ismek.ismekdonem.entity.Otel;
import com.ismek.ismekdonem.listener.HaberListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OtelListAdapter extends RecyclerView.Adapter<OtelListAdapter.OtelViewHolder>{

    private List<Otel> otelList;
    private Context context;
    private HaberListener listener;

    public OtelListAdapter(Context cntx,List<Otel> otels,HaberListener haberListener) {
        this.otelList = otels;
        this.context = cntx;
        this.listener = haberListener;
    }

    @Override
    public OtelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_otel,parent,false);
        return new OtelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OtelViewHolder holder, int position) {
        Otel otel = otelList.get(position);
        holder.txtOtelAdi.setText(otel.getOtelAdi());
        holder.txtLokasyon.setText(otel.getLokasyon());
        holder.txtPromosyon.setText(otel.getPromosyon());
        holder.txtPuan.setText(otel.getPuan() + " / 10");
        holder.txtHostelType.setText(otel.getHostelType());
        holder.txtFiyat.setText(otel.getFiyat() + " TL");
        holder.txtFiyat.setPaintFlags(holder.txtFiyat.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtIndirimliFiyat.setText(otel.getIndirimliFiyat() + " TL");
        holder.txtFiyatBilgi.setText(otel.getFiyatBilgi());
        Picasso.with(context).load(otel.getImageUrl()).into(holder.imgOtel);
    }

    @Override
    public int getItemCount() {
        return otelList.size();
    }

    public class OtelViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgOtel;
        public TextView txtOtelAdi;
        public TextView txtPuan;
        public TextView txtLokasyon;
        public TextView txtPromosyon;
        public TextView txtHostelType;
        public TextView txtFiyat;
        public TextView txtIndirimliFiyat;
        public TextView txtFiyatBilgi;

        public CardView cardView;

        public OtelViewHolder(final View itemView) {
            super(itemView);
            imgOtel = (ImageView)itemView.findViewById(R.id.imgOtel);
            txtOtelAdi = (TextView) itemView.findViewById(R.id.txtOtelAdi);
            txtPuan = (TextView) itemView.findViewById(R.id.txtPuan);
            txtLokasyon = (TextView) itemView.findViewById(R.id.txtLokasyon);
            txtPromosyon = (TextView) itemView.findViewById(R.id.txtPromosyon);
            txtHostelType = (TextView) itemView.findViewById(R.id.txtHostelType);
            txtFiyat = (TextView) itemView.findViewById(R.id.txtFiyat);
            txtIndirimliFiyat = (TextView) itemView.findViewById(R.id.txtIndirimliFiyat);
            txtFiyatBilgi = (TextView) itemView.findViewById(R.id.txtFiyatBilgi);
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
