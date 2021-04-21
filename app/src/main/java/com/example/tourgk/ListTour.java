package com.example.tourgk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tourgk.adapter.TourAdapter;
import com.example.tourgk.adapter.TourFullListAdapter;
import com.example.tourgk.api.ApiClient;
import com.example.tourgk.api.ApiService;
import com.example.tourgk.model.Tour;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTour extends AppCompatActivity {

    RecyclerView rcvListTour;
    TourFullListAdapter tourFullListAdapter;
    List<Tour> tourList = new ArrayList<>();
    ProgressBar progressBar;

    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;

    ApiService service = ApiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tour);

        setControl();
        setEvent();
    }

    private void setEvent() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvListTour.setLayoutManager(layoutManager);
        tourFullListAdapter = new TourFullListAdapter(this, tourList);
        rcvListTour.setAdapter(tourFullListAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ListTour.this, DividerItemDecoration.VERTICAL);
        rcvListTour.addItemDecoration(itemDecoration);

        rcvListTour.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                progressBar.setVisibility(View.VISIBLE);
                currentPage++;

                loadPage(currentPage);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        loadPage(1);
    }

    private void setControl() {
        rcvListTour = findViewById(R.id.rcvListTour);
        progressBar = findViewById((R.id.progress_bar));
    }

    private void loadPage(int page) {
        Call<Tour> tours = service.getTours(page, 9);
        tours.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                Tour tour = response.body();
                if (tour.getListResult().size() == 0) {
                    isLastPage = true;
                }
                tourList.addAll(tour.getListResult());
                tourFullListAdapter.notifyDataSetChanged();//cap nhat du lieu

                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void chuyenTrangDetail(Long id){
        Intent intent = new Intent(this, TourDetail.class);
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}