package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.Client;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Signup extends AppCompatActivity {

    EditText edtNameClientSignup, edtPassword, edtEmail, edtPhoneNumber, edtAddress;
    Button btnSignupConfirm;
    RadioButton radioBtnMale, radioBtnFemale;
    RadioGroup radioGroup;

    AwesomeValidation awesomeValidation;
    ApiService service = ApiClient.getClient().create(ApiService.class);

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
                if (awesomeValidation.validate()) {
                    Toast.makeText(Signup.this, "Validation success...", Toast.LENGTH_LONG).show();

                    Client client = new Client(edtNameClientSignup.getText().toString().trim(),
                            radioBtnMale.isChecked(), edtEmail.getText().toString(),
                            edtEmail.getText().toString(), edtPhoneNumber.getText().toString(),
                            edtPassword.getText().toString());

                    Call<Client> account = service.createAccount(client);
                    account.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(Signup.this, "Fail", Toast.LENGTH_LONG).show();
                                return;
                            }

                            Client client1 = response.body();
                            Toast.makeText(Signup.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {
                            Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                    //finish();
                } else {
                    Toast.makeText(Signup.this, "Validation fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setControl() {
        edtPassword = findViewById(R.id.edtPasswordSignup);
        edtEmail = findViewById(R.id.edtEmailSignup);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumberSignup);
        edtAddress = findViewById(R.id.edtAddressSignup);
        btnSignupConfirm = findViewById(R.id.btnSignupConfirm);
        radioBtnFemale = findViewById(R.id.radioBtnFemale);
        radioBtnMale = findViewById(R.id.radioBtnMale);
        radioGroup = findViewById(R.id.radioGroup);
        edtNameClientSignup = findViewById(R.id.edtNameClientSignup);

        awesomeValidation = new AwesomeValidation(BASIC);

        awesomeValidation.addValidation(this, R.id.edtEmailSignup, Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.edtPasswordSignup, RegexTemplate.NOT_EMPTY, R.string.err_pass);
        awesomeValidation.addValidation(this, R.id.edtNameClientSignup, "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", R.string.err_name);
        awesomeValidation.addValidation(this, R.id.edtPhoneNumberSignup, RegexTemplate.TELEPHONE, R.string.err_phone);
        awesomeValidation.addValidation(this, R.id.edtAddressSignup, RegexTemplate.NOT_EMPTY, R.string.err_address);
    }
}