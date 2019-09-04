package com.gustu.logbook.main.adapter.kegiatan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gustu.logbook.R;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.ViewHolder> {
    List<Kegiatan> kegiatanList;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public KegiatanAdapter(List<Kegiatan> kegiatanList) {
        this.kegiatanList = kegiatanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kegiatan_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kegiatan kegiatan = kegiatanList.get(position);
        holder.namaKegiatan.setText(kegiatan.getMKLNAMA());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefUtil.saveString("nama_kegiatan",kegiatanList.get(position).getMKLNAMA());
                SharedPrefUtil.saveString("keterangan_kegiatan",kegiatanList.get(position).getMKLKETERANGAN());
            }
        });
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefUtil.saveString("nama_kegiatan",kegiatanList.get(position).getMKLNAMA());
                SharedPrefUtil.saveString("keterangan_kegiatan",kegiatanList.get(position).getMKLKETERANGAN());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (kegiatanList!=null){
            return kegiatanList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radioKegiatan)
        RadioButton radioButton;
        @BindView(R.id.tvnamaKegiatan)
        TextView namaKegiatan;
        @BindView(R.id.layoutitemkegiatan)
        RelativeLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
