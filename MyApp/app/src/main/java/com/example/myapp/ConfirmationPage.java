package com.example.myapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation_page);

        Intent intent = getIntent();
        String reservationNumber = intent.getStringExtra("reservationNumber");

        ApiInterface apiService = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<ReservationData> call = apiService.getReservationByNumber(reservationNumber);

        call.enqueue(new Callback<ReservationData>() {
            @Override
            public void onResponse(Call<ReservationData> call, Response<ReservationData> response) {
                if (response.isSuccessful()) {
                    ReservationData user = response.body();
                    Log.d("API", "Name: " + user.getConfirmationNumber());
                } else {
                    Log.e("API", "Response failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ReservationData> call, Throwable t) {
                Log.e("API", "Request failed: " + t.getMessage());
            }
        });

        Button returnBtn = findViewById(R.id.Return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToTheMain = new Intent(ConfirmationPage.this, MainActivity.class);
                startActivity(returnToTheMain);
            }
        });



    }
}