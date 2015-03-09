package com.ronry.helloandroid.criminal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ronry.helloandroid.R;

public class SingleCriminalViewFragment extends Fragment {

    private Criminal criminal;
    private TextView titleView;

    public static SingleCriminalViewFragment newInstance(Criminal criminal) {
        Bundle argunemt = new Bundle();
        argunemt.putSerializable("CRIMINAL", criminal);
        SingleCriminalViewFragment fragment = new SingleCriminalViewFragment();
        fragment.setArguments(argunemt);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argument = this.getArguments();
        criminal = (Criminal) argument.getSerializable("CRIMINAL");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.fragment_criminal_intent, null);
        titleView = (TextView) view.findViewById(R.id.criminal_intent_fragment_textview_title);
        titleView.setText(criminal.getTitle());
        return view;
    }

}
