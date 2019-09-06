package com.gustu.logbook.fragment.profile


import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.gustu.logbook.R
import com.gustu.logbook.login.view.LoginActivity
import com.gustu.logbook.sharePreferences.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        view.nip.text = SharedPrefUtil.getString("nip")
        view.namaUser.text = SharedPrefUtil.getString("nama")
        view.btKeluar.setOnClickListener {
            SharedPrefUtil.saveBoolean("isLogin",false)
            startActivity(Intent(activity, LoginActivity::class.java))
            activity!!.finish()
        }
        // Inflate the layout for this fragment
        return view
    }

}// Required empty public constructor
