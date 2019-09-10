package com.gustu.logbook.fragment.home.delete

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.gustu.logbook.R
import dmax.dialog.SpotsDialog

class Delete(internal var context: Context) {
    lateinit var progressDialog:SpotsDialog
    fun dialogDelete (idLogbook:String,context: Context){
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setMessage("Apakah Anda Yakin Ingin Menghapus Logbook?")
        alertDialog.setTitle("Peringatan")
        alertDialog.setPositiveButton("YA",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    progressDialog = SpotsDialog(context, R.style.DialogCustom)
                    progressDialog.show()
                })
        alertDialog.setNegativeButton("BATAL",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
        alertDialog.show()
    }
}