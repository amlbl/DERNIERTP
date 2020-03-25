package com.example.tpfinal;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class MainActivity extends AppCompatActivity {
    private String API_BASE_URL = "https://restcountries.eu";
    private ArrayList<Country> countries = new Country<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        callWithRetroﬁt();
    }

    private void callWithRetroﬁt() {
     Retrofit retrofit = new Retrofit.Builder()
     .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
     .build();

     MyService service = retrofit.create(MyService.class);
     Call<List<Country>> listCountry = service.getCountry();
     listCountry.enqueue(new Callback<List<Country>>() {
         @Override
         public void onResponse (Call<List<Country>> call, Response<List<Country>> response) {
             countries.addAll(response.body());
             Log.d(" Nb Pays" + countries.size());
         }

         });
     }
    }
}

