package au.edu.unsw.infs3634_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import au.edu.unsw.infs3634_lab.api.Crypto;
import au.edu.unsw.infs3634_lab.recyclerview_adapter.CryptoAdapter;
import au.edu.unsw.infs3634_lab.recyclerview_adapter.RecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CryptoAdapter adapter;

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
        adapter = new CryptoAdapter(Crypto.getCryptoCurrencies(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void rowOnClick(String symbol) {
        Log.d(TAG, "onClick clicked on MainActivity");
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("key", symbol);
        startActivity(intent);
    }
}