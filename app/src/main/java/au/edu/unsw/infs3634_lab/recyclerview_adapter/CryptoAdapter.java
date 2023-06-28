package au.edu.unsw.infs3634_lab.recyclerview_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import au.edu.unsw.infs3634_lab.R;
import au.edu.unsw.infs3634_lab.api.Crypto;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> {
    private ArrayList<Crypto> localDataSet;
    private RecyclerViewClickListener localListener;

    public CryptoAdapter(ArrayList<Crypto> dataset, RecyclerViewClickListener listener) {
        localDataSet = dataset;
        localListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view, localListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Crypto crypto = localDataSet.get(position);
        holder.name.setText(crypto.getName());
        holder.value.setText(crypto.getPriceUsd());
        holder.change.setText(crypto.getPercentChange1h());
        holder.itemView.setTag(crypto.getSymbol());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name, value, change;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.ivCrypto);
            name = itemView.findViewById(R.id.tvRowName);
            value = itemView.findViewById(R.id.tvRowValue);
            change = itemView.findViewById(R.id.tvRowChange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.rowOnClick((String) itemView.getTag());
                }
            });
        }
    }
}
