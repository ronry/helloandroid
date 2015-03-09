package com.ronry.helloandroid.criminal;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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

    private final int CHOOSE_DATE_REQUEST   = 1;
    private final int CHOOSE_PERSON_REQUEST = 2;

    private EditText titleEditor;
    private Button   dateButton;
    private CheckBox solvedCheckBox;
    private Button   choosePersonButton;
    private Button   sendButton;

    private Criminal criminal;

    public static final Fragment newInstance(String id) {
        Bundle argument = new Bundle();
        argument.putString("ID", id);
        Fragment fragment = new CriminalIntentFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle argument = this.getArguments();
        this.criminal = CriminalManager.get(this.getActivity()).getCriminal(argument.getString("ID"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case CHOOSE_DATE_REQUEST: {
                
            }
            case CHOOSE_PERSON_REQUEST: {
                
                Cursor cursor = CriminalIntentFragment.this.getActivity().getContentResolver().query(data.getData(),
                                                                                     new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                                                                                     null, null, null);
                cursor.moveToFirst();
                this.criminal.setPerson(cursor.getString(0));
                choosePersonButton.setText(this.criminal.getPerson());
                cursor.close();
            }
            default:
        }

        this.criminal.setDate((Date) data.getSerializableExtra("DATE"));
        showDate();
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
        showDate();
        dateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CriminalDatePickerFragment datePickFragment = CriminalDatePickerFragment.newInstance(criminal.getDate());

                datePickFragment.setTargetFragment(CriminalIntentFragment.this, CHOOSE_DATE_REQUEST);
                datePickFragment.show(CriminalIntentFragment.this.getFragmentManager(), "CriminalDatePickerFragment");
            }
        });

        solvedCheckBox = (CheckBox) view.findViewById(R.id.criminal_intent_fragment_checkbox_solved);
        solvedCheckBox.setChecked(this.criminal.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                criminal.setSolved(arg1);
            }
        });
        
        this.choosePersonButton = (Button) view.findViewById(R.id.criminal_intent_activity_button_choose_person);
        choosePersonButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                CriminalIntentFragment.this.startActivityForResult(intent, CHOOSE_PERSON_REQUEST);
            }
        });

        this.sendButton = (Button) view.findViewById(R.id.criminal_intent_activit_button_send);
        this.sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, criminal.getTitle());
                intent.putExtra(Intent.EXTRA_SUBJECT, criminal.getTitle());
                intent.putExtra(Intent.EXTRA_PHONE_NUMBER, criminal.getTitle());
                CriminalIntentFragment.this.startActivity(intent);
            }
        });

        return view;

    }

    private void showDate() {
        dateButton.setText(criminal.getDate().toLocaleString());
    }

}
