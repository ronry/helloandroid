package com.ronry.helloandroid.criminal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.ronry.helloandroid.R;


public class CriminalIntentFragment extends Fragment {

    private EditText titleEditor;
    private Button   dateButton;
    private CheckBox solvedCheckBox;

    private Criminal criminal;

    public static final Fragment newInstance(String id) {
        return new CriminalIntentFragment(id);
    }

    private CriminalIntentFragment(String id){
        this.criminal = CriminalManager.get(this.getActivity()).getCriminal(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_criminal_intent, container, Boolean.FALSE);
        
        titleEditor = (EditText) view.findViewById(R.id.criminal_intent_fragment_title_editor);
        titleEditor.setText(this.criminal.getTitle());
        titleEditor.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                criminal.setTitle(arg0.toString());
            }
            
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                
            }
            
            @Override
            public void afterTextChanged(Editable arg0) {
                
            }
        });

        dateButton = (Button) view.findViewById(R.id.criminal_intent_fragment_button_date);
        dateButton.setText(criminal.getDate().toLocaleString());
        dateButton.setEnabled(false);

        solvedCheckBox = (CheckBox) view.findViewById(R.id.criminal_intent_fragment_checkbox_solved);
        solvedCheckBox.setChecked(this.criminal.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                criminal.setSolved(arg1);
            }
        });

        return view;
    }

}
