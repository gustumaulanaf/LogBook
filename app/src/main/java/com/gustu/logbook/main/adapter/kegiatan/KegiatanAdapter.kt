package com.gustu.logbook.main.adapter.kegiatan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.gustu.logbook.R
import com.gustu.logbook.main.model.kegiatan.Kegiatan
import com.gustu.logbook.sharePreferences.SharedPrefUtil
import kotlinx.android.synthetic.main.item_kegiatan_dialog.view.*

class KegiatanAdapter(internal var kegiatanList: List<Kegiatan>?) : RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {
    internal lateinit var context: Context
    internal var mCheckPosition = 0
    fun setContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kegiatan_dialog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kegiatan = kegiatanList!![position]
        holder.namaKegiatan.text = kegiatan.mklnama
        holder.linearLayout.setOnClickListener {
            SharedPrefUtil.saveString("nama_kegiatan", kegiatanList!![position].mklnama!!)
            SharedPrefUtil.saveString("keterangan_kegiatan", kegiatanList!![position].mklketerangan)
            SharedPrefUtil.saveString("kode_kegiatan", kegiatanList!![position].mklkode!!)
        }

        holder.radioButton.isChecked = position == mCheckPosition
        holder.radioButton.setOnClickListener {
            if (position == mCheckPosition) {
                holder.radioButton.isChecked = false
                mCheckPosition = -1
            } else {
                mCheckPosition = position
                notifyDataSetChanged()
            }
            SharedPrefUtil.saveString("nama_kegiatan", kegiatanList!![position].mklnama!!)
          SharedPrefUtil.saveString("keterangan_kegiatan", kegiatanList!![position].mklketerangan)
            SharedPrefUtil.saveString("kode_kegiatan", kegiatanList!![position].mklkode!!)
        }
    }

    override fun getItemCount(): Int {
        return if (kegiatanList != null) {
            kegiatanList!!.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var radioButton = itemView.radioKegiatan
                internal var namaKegiatan=itemView.tvnamaKegiatan
        internal var linearLayout = itemView.layoutitemkegiatan
    }
}
