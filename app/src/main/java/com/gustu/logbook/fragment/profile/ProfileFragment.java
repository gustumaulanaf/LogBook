package com.gustu.logbook.fragment.profile;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gustu.logbook.R;
import com.gustu.logbook.login.view.LoginActivity;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.nip)
    TextView nip;
    @BindView(R.id.namaUser)
    TextView nama;
    @BindView(R.id.btKeluar)
    Button logout;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        nip.setText(SharedPrefUtil.getString("nip"));
        nama.setText(SharedPrefUtil.getString("nama"));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
