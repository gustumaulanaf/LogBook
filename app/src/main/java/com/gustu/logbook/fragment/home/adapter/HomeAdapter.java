package com.gustu.logbook.fragment.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gustu.logbook.R;
import com.gustu.logbook.fragment.home.model.DataItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<DataItem> dataItemList;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public HomeAdapter(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_logbook,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem dataItem = dataItemList.get(position);
        if (position%2==0){
            holder.layout.setBackgroundColor(Color.GRAY);
        }
        else{
            holder.layout.setBackgroundColor(Color.WHITE);
        }
        holder.namaKegiatan.setText(dataItem.getTLBNAMAKEGIATAN());
        holder.tanggal.setText(String.valueOf(dataItem.getTLBTANGGAL()));
    }

    @Override
    public int getItemCount() {
        if (dataItemList!=null){
            return dataItemList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.namaKegiatanLog)
        TextView namaKegiatan;
        @BindView(R.id.tanggalLog)
        TextView tanggal;
        @BindView(R.id.btEdit)
        ImageView edit;
        @BindView(R.id.btHapus)
        ImageView hapus;
        @BindView(R.id.layoutItemLogbook)
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
