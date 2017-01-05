package com.example.spidey.helloworld.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.spidey.helloworld.Asset.DbHelperClass;
import com.example.spidey.helloworld.R;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText mFirstname, mLastname, mUsername, mEmail, mContact, mPassword, mCpassword;

    private RadioGroup mGenderGroup;

    private Button mSaveBtn, mDob;

    private RadioButton mMale, mFemale, mGender;

    DbHelperClass database;

    private String mDataFirstname, mDataLastname, mDataUsername, mDataEmail, mDataContact, mDataPassword, mDataCpassword, mDataDOB, mDataGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstname = (EditText) findViewById(R.id.firstName);
        mLastname = (EditText) findViewById(R.id.lastName);
        mUsername = (EditText) findViewById(R.id.userName);
        mEmail = (EditText) findViewById(R.id.email);
        mContact = (EditText) findViewById(R.id.contact);
        mPassword = (EditText) findViewById(R.id.password);
        mCpassword = (EditText) findViewById(R.id.cPassword);

        mGenderGroup = (RadioGroup) findViewById(R.id.gender);

        mSaveBtn = (Button) findViewById(R.id.btn_save);
        mDob = (Button) findViewById(R.id.dob);

        mMale = (RadioButton) findViewById(R.id.male);
        mFemale = (RadioButton) findViewById(R.id.female);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int calendar_year = view.getYear();
                        int calendar_month = view.getMonth() + 1;
                        int calendar_day = view.getDayOfMonth();

                        mDataDOB = String.valueOf(calendar_year) + "-" + String.valueOf(calendar_month) + "-" + String.valueOf(calendar_day);

                        mDob.setText("Date of Birth: " + String.valueOf(calendar_year) + "/ " + String.valueOf(calendar_month) + "/ " + String.valueOf(calendar_day));

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = mGenderGroup.getCheckedRadioButtonId();

                mGender = (RadioButton) findViewById(selectedId);

                mDataGender = mGender.getText().toString();

//                Toast.makeText(RegisterActivity.this, mGender.getText().toString(), Toast.LENGTH_LONG).show();

                mDataFirstname = mFirstname.getText().toString();
                mDataLastname = mLastname.getText().toString();
                mDataUsername = mUsername.getText().toString();
                mDataEmail = mEmail.getText().toString();
                mDataContact = mContact.getText().toString();
                mDataPassword = mPassword.getText().toString();
                mDataCpassword = mCpassword.getText().toString();


                if (!mDataFirstname.equals("") && !mDataLastname.equals("") && !mDataUsername.equals("") && !mDataEmail.equals("") && !mDataPassword.equals("") && !mDataCpassword.equals("")) {

                    if (mDataPassword.equals(mDataCpassword)) {
                        database = new DbHelperClass(RegisterActivity.this);
                        boolean result = database.insertInfo(mDataFirstname, mDataLastname, mDataUsername, mDataContact, mDataEmail, mDataPassword, mDataDOB, mDataGender);
                        if (result == true) {
                            Toast.makeText(RegisterActivity.this, "Your Data has been successfully inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                } else {
//                    mFirstname.setError("Please enter some value");
                }


//                if (id == mFemale.getId()) {
//                    Toast.makeText(RegisterActivity.this, mFemale.getText().toString(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(RegisterActivity.this, mMale.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}
