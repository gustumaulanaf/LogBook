package com.gustu.logbook.fragment.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.gustu.logbook.R
import com.gustu.logbook.fragment.home.interfaces.HomeView
import com.gustu.logbook.fragment.home.model.GetLogBook
import com.gustu.logbook.fragment.home.presenter.DeletePresenter
import com.gustu.logbook.fragment.home.presenter.HomePresenter
import com.gustu.logbook.fragment.home.view.HomeFragment
import com.gustu.logbook.main.view.MainActivity
import kotlinx.android.synthetic.main.item_home_logbook.view.*

class HomeAdapter(internal var dataItemList: List<GetLogBook>?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    internal lateinit var context: Context
    internal lateinit var homeFragment: HomeFragment
    fun setContext(context: Context , homeFragment: HomeFragment) {
        this.context = context
        this.homeFragment = homeFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_logbook, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = dataItemList!![position]
//        if (position % 2 == 0) {
//            holder.layout!!.setBackgroundColor(Color.LTGRAY)
//        } else {
//            holder.layout!!.setBackgroundColor(Color.WHITE)
//        }
        holder.namaKegiatan!!.text = dataItem.tLBNAMAKEGIATAN!!
        holder.tanggal!!.text = dataItem.tLBTANGGAL
        holder.hapus.setOnClickListener {
            homeFragment.deleteData(dataItemList!!.get(position).tLBID!!)
        }
    }

    override fun getItemCount(): Int {
        return if (dataItemList != null) {
            dataItemList!!.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var namaKegiatan = itemView.namaKegiatanLog
        internal var tanggal = itemView.tanggalLog
        internal var edit = itemView.btEdit
        internal var hapus = itemView.btHapus
        internal var layout = itemView.layoutItemLogbook
    }
}
