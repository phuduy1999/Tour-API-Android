package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourgk.adapter.TourAdapter;
import com.example.tourgk.adapter.TourFullListAdapter;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.Tour;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView tourRecycler;
    TourAdapter tourAdapter;

    RecyclerView topTourRecycler;
    TourFullListAdapter topTourAdapter;

    TextView tvSeeAll;
    ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setControl() {
        tvSeeAll = findViewById(R.id.tvSeeAll);
        tourRecycler = findViewById(R.id.rcvNewTours);
        topTourRecycler = findViewById(R.id.rcvTopTours);
        imgUser = findViewById(R.id.imgUser);
    }

    private void setEvent() {
        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListTour.class);
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Signin.class);
                startActivity(intent);
            }
        });

        //call API
        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Tour> tours = service.getTours(1, 9);
        tours.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Tour tour = response.body();

                setTourRecycler(tour.getListResult());
                setTopTourRecycler(tour.getListResult());
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTourRecycler(List<Tour> tourList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tourRecycler.setLayoutManager(layoutManager);
        tourAdapter = new TourAdapter(this, tourList);
        tourRecycler.setAdapter(tourAdapter);
    }

    private void setTopTourRecycler(List<Tour> tourList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topTourRecycler.setLayoutManager(layoutManager);
        topTourAdapter = new TourFullListAdapter(this, tourList);
        topTourRecycler.setAdapter(topTourAdapter);
    }

    public void chuyenTrangDetail(Long id) {
        Intent intent = new Intent(this, TourDetail.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}