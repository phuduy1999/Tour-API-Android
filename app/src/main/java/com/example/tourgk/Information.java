package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Information extends AppCompatActivity {

    TextView tvUsername, edtEmail, edtPhoneNumber, edtAddress, edtNameClient;
    Button btnEdit;
    RadioButton radioBtnMale, radioBtnFemale;
    RadioGroup radioGroup;
    Long userID;

    AwesomeValidation awesomeValidation;
    ApiService service = ApiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        setControl();
        setEvent();
    }

    private void setEvent() {
        Client cll = new Client(Signin.username);

        Call<Client> cl = service.getClient(cll);
        cl.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Information.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Client client = response.body();
                userID = client.getUserID();
                tvUsername.setText(client.getEmail());
                edtNameClient.setText(client.getName());
                edtPhoneNumber.setText(client.getPhoneNumber());
                edtEmail.setText(client.getEmail());
                edtPhoneNumber.setText(client.getPhoneNumber());
                edtAddress.setText(client.getAddress());
                if (client.isGender()) {
                    radioBtnMale.setChecked(true);
                } else radioBtnFemale.setChecked(true);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(Information.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(Information.this, "Validation success...", Toast.LENGTH_LONG).show();

                    Client client = new Client(userID, edtNameClient.getText().toString().trim(),
                            radioBtnMale.isChecked(), edtEmail.getText().toString(),
                            edtEmail.getText().toString(), edtPhoneNumber.getText().toString());

                    Call<Client> account = service.updateAccount(client);
                    account.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(Information.this, "Fail", Toast.LENGTH_LONG).show();
                                return;
                            }

                            Client client1 = response.body();
                            Toast.makeText(Information.this, "Cập nhật thông tin thành công!", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(getIntent());
                        }

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {
                            Toast.makeText(Information.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(Information.this, "Validation fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setControl() {
        tvUsername = findViewById(R.id.tvUsernameInfo);
        edtEmail = findViewById(R.id.edtEmailInfo);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumberInfo);
        edtAddress = findViewById(R.id.edtAddressInfo);
        btnEdit = findViewById(R.id.btnEdit);
        radioBtnFemale = findViewById(R.id.radioBtnFemaleInfo);
        radioBtnMale = findViewById(R.id.radioBtnMaleInfo);
        radioGroup = findViewById(R.id.radioGroupInfo);
        edtNameClient = findViewById(R.id.edtNameClientInfo);

        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(this, R.id.edtEmailInfo, Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.edtNameClientInfo, "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", R.string.err_name);
        awesomeValidation.addValidation(this, R.id.edtPhoneNumberInfo, RegexTemplate.TELEPHONE, R.string.err_phone);
        awesomeValidation.addValidation(this, R.id.edtAddressInfo, RegexTemplate.NOT_EMPTY, R.string.err_address);
    }
}