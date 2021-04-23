package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Information extends AppCompatActivity {

    TextView tvUsername, tvEmail, tvBirthDay, tvPhoneNumber, tvAddress,tvNameClient;
    Button btnEdit;
    RadioButton radioBtnMale, radioBtnFemale;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setControl() {
        tvUsername = findViewById(R.id.tvUsernameInfo);
        tvEmail = findViewById(R.id.tvEmailInfo);
        tvBirthDay = findViewById(R.id.tvBirthDayInfo);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumberInfo);
        tvAddress = findViewById(R.id.tvAddressInfo);
        btnEdit = findViewById(R.id.btnEdit);
        radioBtnFemale = findViewById(R.id.radioBtnFemaleInfo);
        radioBtnMale = findViewById(R.id.radioBtnMaleInfo);
        radioGroup = findViewById(R.id.radioGroupInfo);
        tvNameClient=findViewById(R.id.tvNameClientInfo);
    }
}