package com.example.fatecdsm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIrVestibular, btnIrVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIrVestibular = findViewById(R.id.btnIrVestibular);
        btnIrVideo = findViewById(R.id.btnIrVideo);

        // Intent para navegar até a Tela 2 (Vestibular)
        btnIrVestibular.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VestibularActivity.class);
            startActivity(intent);
        });

        // Intent para navegar até a Tela 3 (Vídeo do Curso)
        btnIrVideo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        });
    }
}
