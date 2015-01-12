package com.ronry.helloandroid.criminal;

import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ronry.helloandroid.R;


public class CriminalListFragment extends ListFragment {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(new CriminalListAdapter(CriminalManager.get(getActivity()).getCriminals()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), CriminialViewPagerActivity.class);
        intent.putExtra("position", position);
        getActivity().startActivity(intent);
    }

    private class CriminalListAdapter extends ArrayAdapter<Criminal> {

        public CriminalListAdapter(List<Criminal> objects){
            super(getActivity(), 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 尽量重用
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_criminal, null);
            }

            Criminal criminal = getItem(position);

            ((TextView) convertView.findViewById(R.id.criminal_list_title)).setText(criminal.getTitle());
            ((TextView) convertView.findViewById(R.id.criminal_list_date)).setText(criminal.getDate().toLocaleString());
            ((CheckBox) convertView.findViewById(R.id.criminal_list_solved)).setChecked(criminal.isSolved());

            return convertView;
        }
    }

}
