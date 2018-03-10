package com.example.logonpf.carros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etPlaca;
    private Button btPesquisar;

    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvAno;
    private TextView tvVersao;
    private TextView tvPreço;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlaca = findViewById(R.id.etPlaca);
        btPesquisar = findViewById(R.id.btPesquisar);

        tvMarca = findViewById(R.id.tvMarca);
        tvModelo = findViewById(R.id.tvModelo);
        tvAno = findViewById(R.id.tvAno);
        tvVersao = findViewById(R.id.tvVersao);
        tvPreço = findViewById(R.id.tvPreço);

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisar();
            }
        });
    }

    private void pesquisar() {
        CarroAPI service = getRetrofit().create(CarroAPI.class);
        Call<Carro> carroAPI = service.buscaCarroPor(etPlaca.getText().toString());
        carroAPI.enqueue(new Callback<Carro>() {
            @Override
            public void onResponse(Call<Carro> call, Response<Carro> response) {
                Carro carro = response.body();
                tvMarca.setText(carro.getMarca());
                tvModelo.setText(carro.getModelo());
                tvAno.setText(carro.getAno());
                tvVersao.setText(carro.getVersao());
                tvPreço.setText(carro.getPreco());
            }

            @Override
            public void onFailure(Call<Carro> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://linux2.autorisco.com.br:5500")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
