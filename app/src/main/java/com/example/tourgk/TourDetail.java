package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.BookingRequest;
import com.example.tourgk.model.Tour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourDetail extends AppCompatActivity {

    ImageView imgTour;
    TextView tvTourName, tvLocation, tvPrice, tvDuration, tvStartDate, tvGroupSize;
    Long id;
    Button btnConfirm;

    Long tourId;

    ApiService service = ApiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getLong("id");

        setControl();
        setEvent();
    }

    private void setEvent() {
        Call<Tour> tour = service.getTourById(id);
        tour.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Tour tour = response.body();

                tourId = tour.getId();
                tvTourName.setText(tour.getName());
                tvDuration.setText(String.valueOf(tour.getDuration()) + " ngày");
                tvLocation.setText(tour.getLocation());
                tvPrice.setText(String.valueOf(tour.getPrice()) + " vnd");
                tvStartDate.setText(tour.getStartDate());

                int curGS = tour.getCurrentGroupSize();
                int maxGS = tour.getMaxGroupSize();
                tvGroupSize.setText(String.valueOf(curGS) + "/" + String.valueOf(maxGS));

                Glide.with(TourDetail.this).load(ApiClient.BASE_URL + "template/upload/tour/"
                        + tour.getImageCover()).into(imgTour);
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBooking("phuduy1999@gmail.com", tourId);
            }
        });
    }

    private void booking() {
        //if (isNotBooking == true) {
//            Call<String> result = service.booking(new BookingRequest("phuduy1999@gmail.com",
//                    (long) 14, 6));
//            result.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    String result = response.body();
//                    Toast.makeText(TourDetail.this, result, Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    Toast.makeText(TourDetail.this, "fail", Toast.LENGTH_LONG).show();
//                }
//            });
        // }
    }

    private void checkBooking(String email, Long tourId) {
        Call<String> result = service.checkBooking(new BookingRequest(email, tourId));
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("dddd", "ddddddddddd");
                String kq = response.body();
                Log.e("dddd", "ddddddddddd2" + kq);
                Toast.makeText(TourDetail.this, kq, Toast.LENGTH_LONG).show();
                if (kq.equals("true")) {
                    chuyenTrangPayment();
                } else {
                    Toast.makeText(TourDetail.this, "Bạn đã đặt tour này rồi!",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(TourDetail.this, "fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setControl() {
        imgTour = findViewById(R.id.imgTourDetail);
        tvTourName = findViewById(R.id.tvNameDetail);
        tvLocation = findViewById(R.id.tvLocationDetail);
        tvPrice = findViewById(R.id.tvPriceDetail);
        tvDuration = findViewById(R.id.tvDurationDetail);
        tvStartDate = findViewById(R.id.tvStartDateDetail);
        tvGroupSize = findViewById(R.id.tvGroupSizeDetail);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void chuyenTrangPayment() {
        Intent intent = new Intent(TourDetail.this, Payment.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}