package au.edu.unsw.infs3634_lab.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoService {
    @GET("api/tickers")
    Call<Response> getCryptos();

    @GET("api/ticker/")
    Call<ArrayList<Crypto>> getCrypto(@Query("id") Integer id);
}
