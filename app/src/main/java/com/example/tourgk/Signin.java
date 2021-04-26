package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Signin extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSigninConfirm, btnSignup;

    AwesomeValidation awesomeValidation;
    ApiService service = ApiClient.getClient().create(ApiService.class);

    public static String username = ""; //email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);
            }
        });

        btnSigninConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(Signin.this, "Validation success...", Toast.LENGTH_LONG).show();

                    LoginRequest lo = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());

                    Call<String> result = service.login(lo);
                    result.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(Signin.this, "Fail1", Toast.LENGTH_LONG).show();
                                return;
                            }

                            String kq = response.body();
                            Toast.makeText(Signin.this, kq, Toast.LENGTH_LONG).show();
                            if (!kq.equals("Incorrect username and password") && !kq.equals("Server Error")) {
                                username = edtUsername.getText().toString();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(Signin.this, "Fail", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(Signin.this, "Validation fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setControl() {
        edtUsername = findViewById(R.id.edtUsernameSignin);
        edtPassword = findViewById(R.id.edtPasswordSignin);
        btnSigninConfirm = findViewById(R.id.btnSigninConfirm);
        btnSignup = findViewById(R.id.btnSignup);

        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(this, R.id.edtUsernameSignin, Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.edtPasswordSignin, RegexTemplate.NOT_EMPTY, R.string.err_pass);
    }
}