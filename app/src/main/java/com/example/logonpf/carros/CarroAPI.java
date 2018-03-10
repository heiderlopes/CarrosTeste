package com.example.logonpf.carros;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarroAPI {
    @GET("/api/veiculo")
    Call<Carro> buscaCarroPor(@Query("placa") String placa);
}