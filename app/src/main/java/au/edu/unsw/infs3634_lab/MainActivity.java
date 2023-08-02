package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import au.edu.unsw.infs3634_lab.DB.CryptoDatabase;
import au.edu.unsw.infs3634_lab.api.Crypto;
import au.edu.unsw.infs3634_lab.api.CryptoService;
import au.edu.unsw.infs3634_lab.api.Response;
import au.edu.unsw.infs3634_lab.recyclerview_adapter.CryptoAdapter;
import au.edu.unsw.infs3634_lab.recyclerview_adapter.RecyclerViewClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CryptoAdapter adapter;
    private CryptoDatabase database;

    private final String TAG = "MainActivity";
    private final String msg = "Message from MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate launched and initialized");

        recyclerView = findViewById(R.id.rvList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new CryptoAdapter(new ArrayList<>(), this);

        database = Room.databaseBuilder(getApplicationContext(), CryptoDatabase.class, "myDb")
                .build();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Crypto> cryptos = database.cryptoDao().getCryptos();
                adapter.setData(cryptos);
                adapter.sortList(CryptoAdapter.SORT_METHOD_NAME);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinlore.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CryptoService service = retrofit.create(CryptoService.class);
        Call<Response> responseCall = service.getCryptos();

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d(TAG, "API call successful!");
                List<Crypto> cryptos = response.body().getData();
                adapter.setData(cryptos);
                adapter.sortList(CryptoAdapter.SORT_METHOD_NAME);

                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        database.cryptoDao().deleteAll(database.cryptoDao().getCryptos().toArray(new Crypto[0]));
                        database.cryptoDao().insertAll(cryptos.toArray(new Crypto[0]));
                    }
                });
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "API call failure.");
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void rowOnClick(String symbol) {
        Log.d(TAG, "onClick clicked on MainActivity");
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("key", symbol);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.appBarSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortName:
                adapter.sortList(CryptoAdapter.SORT_METHOD_NAME);
                return true;
            case R.id.sortValue:
                adapter.sortList(CryptoAdapter.SORT_METHOD_VALUE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}