package com.example.fatecdsm;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VestibularActivity extends AppCompatActivity {

    private Button btnVoltarHomeVestibular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vestibular);

        btnVoltarHomeVestibular = findViewById(R.id.btnVoltarHomeVestibular);

        // Retorna para a Página Inicial (MainActivity)
        btnVoltarHomeVestibular.setOnClickListener(v -> finish());
    }
}
