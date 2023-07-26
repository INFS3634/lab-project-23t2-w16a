package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import au.edu.unsw.infs3634_lab.api.Crypto;
import au.edu.unsw.infs3634_lab.api.CryptoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "DetailActivity";
    private TextView mName, mSymbol,mRank, mValue, mChangeHr, mChangeDay, mChangeWk, mMarket, mVolume;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Log.d(TAG, "DetailActivity onCreate launched");
        Intent intent = getIntent();
        String symbol = intent.getStringExtra("key");
        Log.d(TAG, "Symbol is " + symbol);

        mName = findViewById(R.id.tvName);
        mSymbol = findViewById(R.id.tvSymbol);
        mRank = findViewById(R.id.tvRank);
        mValue = findViewById(R.id.tvValue);
        mChangeHr = findViewById(R.id.tvChangeHour);
        mChangeDay = findViewById(R.id.tvChangeDay);
        mChangeWk = findViewById(R.id.tvChangeWeek);
        mMarket = findViewById(R.id.tvMarket);
        mVolume = findViewById(R.id.tvVolume);
        mSearch = findViewById(R.id.ivSearch);

        if (intent.hasExtra("key")) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.coinlore.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CryptoService service = retrofit.create(CryptoService.class);
            Call<ArrayList<Crypto>> responseCall = service.getCrypto(Integer.valueOf(symbol));

            responseCall.enqueue(new Callback<ArrayList<Crypto>>() {
                @Override
                public void onResponse(Call<ArrayList<Crypto>> call, Response<ArrayList<Crypto>> response) {
                    Log.d(TAG, "API Call Successful!" + " URL=" + call.request().url().toString());
                    Crypto crypto = response.body().get(0);
                    if (crypto != null) {
                        mName.setText(crypto.getName());
                        mSymbol.setText(crypto.getSymbol());
                        mRank.setText(String.valueOf(crypto.getRank()));
                        mValue.setText(crypto.getPriceUsd());
                        mChangeHr.setText(crypto.getPercentChange1h());
                        mChangeDay.setText(crypto.getPercentChange24h());
                        mChangeWk.setText(crypto.getPercentChange7d());
                        mMarket.setText(crypto.getMarketCapUsd());
                        mVolume.setText(String.valueOf(crypto.getVolume24()));

                        mSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + symbol));
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Crypto>> call, Throwable t) {
                    Log.d(TAG, "API Call Failure." + " URL=" + call.request().url().toString());
                }
            });
        }


//        if (crypto != null) {
//            mName.setText(crypto.getName());
//            mSymbol.setText(crypto.getSymbol());
//            mRank.setText(String.valueOf(crypto.getRank()));
//            mValue.setText(crypto.getPriceUsd());
//            mChangeHr.setText(crypto.getPercentChange1h());
//            mChangeDay.setText(crypto.getPercentChange24h());
//            mChangeWk.setText(crypto.getPercentChange7d());
//            mMarket.setText(crypto.getMarketCapUsd());
//            mVolume.setText(String.valueOf(crypto.getVolume24()));
//        }
//
//        mSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + symbol));
//                startActivity(intent);
//            }
//        });
    }
}