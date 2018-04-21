package com.ismek.ismekdonem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ismek.ismekdonem.R;
import com.ismek.ismekdonem.entity.Not;
import com.ismek.ismekdonem.listener.HaberListener;

import java.text.SimpleDateFormat;
import java.util.List;

public class NotListAdapter extends RecyclerView.Adapter<NotListAdapter.CustomHolder>{

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    private List<Not> liste;
    private HaberListener haberListener;

    public NotListAdapter(List<Not> list, HaberListener listener){
        this.liste = list;
        this.haberListener = listener;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        Not not = liste.get(position);
        holder.txtAdi.setText(not.getBaslik());
        holder.txtDate.setText(formatter.format(not.getOlusturulmaZamani()));
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_note,parent,false);
        return new CustomHolder(view);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder{

        public LinearLayout llContent;
        public TextView txtAdi;
        public TextView txtDate;

        public CustomHolder(View view){
            super(view);
            txtAdi = (TextView) view.findViewById(R.id.txtAdi);
            txtDate = (TextView) view.findViewById(R.id.txtDate);
            llContent = (LinearLayout) view.findViewById(R.id.llContent);
            llContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    haberListener.onDetail(view,getAdapterPosition());
                }
            });
        }

    }

}
