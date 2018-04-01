package com.ismek.ismekdonem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ismek.ismekdonem.R;
import com.ismek.ismekdonem.entity.Kategori;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriHolder>{

    private List<Kategori> kategoriList;

    public KategoriAdapter(List<Kategori> list){
        this.kategoriList = list;
    }

    @Override
    public void onBindViewHolder(KategoriHolder holder, int position) {
        Kategori kategori = kategoriList.get(position);
        holder.txtAdi.setText(kategori.getAdi());
    }

    @Override
    public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_kategori,parent,false);
        return new KategoriHolder(view);
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public class KategoriHolder extends RecyclerView.ViewHolder{

        public LinearLayout llContent;
        public TextView txtAdi;

        public KategoriHolder(View view){
            super(view);
            txtAdi = (TextView) view.findViewById(R.id.txtAdi);
            llContent = (LinearLayout) view.findViewById(R.id.llContent);
        }

    }

}
