package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.bumptech.glide.Glide;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.BookingRequest;
import com.example.tourgk.model.Client;
import com.example.tourgk.model.LoginRequest;
import com.example.tourgk.model.Tour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Payment extends AppCompatActivity {

    ImageView imgTour;
    TextView tvNameTour, tvDescriptionTour, tvPriceTour, tvPriceTour2, tvPriceTotal, tvNameClient, tvSDT;
    EditText edtSoLuong;
    Button btnDatTour;

    Long price;
    AwesomeValidation awesomeValidation;

    Long tourId;
    ApiService service = ApiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tourId = bundle.getLong("id");

        setControl();
        setEvent();
    }

    private void setEvent() {
        Client cll=new Client(Signin.username);

        Call<Client> cl=service.getClient(cll);
        cl.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Payment.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Client client=response.body();
                tvNameClient.setText(client.getName());
                tvSDT.setText(client.getPhoneNumber());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(Payment.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<Tour> tour = service.getTourById(tourId);
        tour.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Payment.this, response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Tour tour = response.body();

                tvNameTour.setText(tour.getName());
                tvDescriptionTour.setText(tour.getDescription());
                tvPriceTour.setText(String.valueOf(tour.getPrice()) + " VND");
                tvPriceTour2.setText(String.valueOf(tour.getPrice() * (Integer.parseInt(edtSoLuong.getText().toString()))) + " VND");
                price = tour.getPrice();
                tvPriceTotal.setText(tvPriceTour2.getText().toString());
                Glide.with(Payment.this).load(ApiClient.BASE_URL + "template/upload/tour/"
                        + tour.getImageCover()).into(imgTour);
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(Payment.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        edtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtSoLuong.getText().toString().isEmpty()) {
                    int sl = Integer.parseInt(edtSoLuong.getText().toString());
                    tvPriceTour2.setText(String.valueOf(price * sl) + " VND");
                    tvPriceTotal.setText(tvPriceTour2.getText().toString());
                } else {
                    tvPriceTour2.setText(String.valueOf(price) + " VND");
                    tvPriceTotal.setText(tvPriceTour2.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(Payment.this, "Validation success...", Toast.LENGTH_LONG).show();

                    BookingRequest bookingRequest=new BookingRequest(Signin.username,
                            tourId, Integer.parseInt(edtSoLuong.getText().toString()));

                    Call<String> result = service.booking(bookingRequest);
                    result.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            Toast.makeText(Payment.this, "Bạn đã book tour thành công!: " + result,
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(Payment.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(Payment.this, "Validation fail", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setControl() {
        imgTour = findViewById(R.id.imgTourPayment2);
        tvNameTour = findViewById(R.id.tvNameTourPayment);
        tvDescriptionTour = findViewById(R.id.tvDescriptionTourPayment);
        tvPriceTour = findViewById(R.id.tvPricePayment);
        tvPriceTour2 = findViewById(R.id.tvTienHang);
        tvPriceTotal = findViewById(R.id.tvTongCong);
        tvNameClient = findViewById(R.id.tvNameClientPayment);
        tvSDT = findViewById(R.id.tvPhoneNumberPayment);
        edtSoLuong = findViewById(R.id.edtSoLuongPayment);
        btnDatTour = findViewById(R.id.btnDatTour);

        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(this, R.id.edtSoLuongPayment, RegexTemplate.NOT_EMPTY, R.string.err_slp);
    }
}