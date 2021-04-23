package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    TextView tvUsername, tvNameClientSignup, tvPassword, tvEmail, tvBirthDay, tvPhoneNumber, tvAddress;
    Button btnSignupConfirm;
    RadioButton radioBtnMale, radioBtnFemale;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnSignupConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        tvUsername = findViewById(R.id.tvUsernameSignup);
        tvPassword = findViewById(R.id.tvPasswordSignup);
        tvEmail = findViewById(R.id.tvEmailSignup);
        tvBirthDay = findViewById(R.id.tvBirthDaySignup);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumberSignup);
        tvAddress = findViewById(R.id.tvAddressSignup);
        btnSignupConfirm = findViewById(R.id.btnSignupConfirm);
        radioBtnFemale = findViewById(R.id.radioBtnFemale);
        radioBtnMale = findViewById(R.id.radioBtnMale);
        radioGroup = findViewById(R.id.radioGroup);
        tvNameClientSignup = findViewById(R.id.tvNameClientSignup);
    }
}