package com.ronry.helloandroid.criminal;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ronry.helloandroid.R;


public class CriminalIntentFragment extends Fragment {

    private EditText titleEditor;

    private Criminal criminal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        criminal = new Criminal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_criminal_intent, container, Boolean.FALSE);
        
        titleEditor = (EditText) view.findViewById(R.id.criminal_intent_fragment_title_editor);
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

        return view;
    }

}
