package com.ronry.helloandroid.criminal;

import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.ronry.helloandroid.R;

public class CriminalDatePickerFragment extends DialogFragment {

    private Date date;

    public static final CriminalDatePickerFragment newInstance(Date date) {
        Bundle argument = new Bundle();
        argument.putSerializable("DATE", date);

        CriminalDatePickerFragment fragment = new CriminalDatePickerFragment();
        fragment.setArguments(argument);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.fragment_date_dia, null);

        date = (Date) this.getArguments().getSerializable("DATE");
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.criminal_date_picker);
        datePicker.init(2016, 04, 11, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            }
        });

        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("DATE", date);
                CriminalDatePickerFragment.this.getTargetFragment().onActivityResult(0, Activity.RESULT_OK, intent);
            }
        };
        return new AlertDialog.Builder(this.getActivity()).setView(view).setPositiveButton("OK", listener).create();
    }

}
