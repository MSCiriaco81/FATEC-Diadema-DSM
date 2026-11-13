package com.example.fatecdsm;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoViewCurso;
    private SeekBar seekBarVideo;
    private Button btnPlayVideo, btnPauseVideo, btnStopVideo, btnVoltarHomeVideo;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean isTracking = false;

    // Runnable para atualizar a SeekBar continuamente durante a reprodução
    private final Runnable atualizarSeekBarRunnable = new Runnable() {
        @Override
        public void run() {
            if (videoViewCurso != null && videoViewCurso.isPlaying() && !isTracking) {
                seekBarVideo.setProgress(videoViewCurso.getCurrentPosition());
            }
            handler.postDelayed(this, 200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoViewCurso = findViewById(R.id.videoViewCurso);
        seekBarVideo = findViewById(R.id.seekBarVideo);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);
        btnPauseVideo = findViewById(R.id.btnPauseVideo);
        btnStopVideo = findViewById(R.id.btnStopVideo);
        btnVoltarHomeVideo = findViewById(R.id.btnVoltarHomeVideo);

        // Carrega o vídeo institucional da pasta res/raw
        prepararVideo();

        // Define a duração máxima da SeekBar quando o vídeo estiver pronto
        videoViewCurso.setOnPreparedListener(mp -> {
            seekBarVideo.setMax(videoViewCurso.getDuration());
        });

        // Ao finalizar o vídeo, zera a SeekBar
        videoViewCurso.setOnCompletionListener(mp -> {
            seekBarVideo.setProgress(0);
        });

        // Botão INICIAR
        btnPlayVideo.setOnClickListener(v -> {
            if (!videoViewCurso.isPlaying()) {
                videoViewCurso.start();
                handler.removeCallbacks(atualizarSeekBarRunnable);
                handler.post(atualizarSeekBarRunnable);
            }
        });

        // Botão PAUSAR
        btnPauseVideo.setOnClickListener(v -> {
            if (videoViewCurso.isPlaying()) {
                videoViewCurso.pause();
            }
        });

        // Botão STOP
        btnStopVideo.setOnClickListener(v -> pararVideo());

        // Botão Voltar para a Página Inicial
        btnVoltarHomeVideo.setOnClickListener(v -> {
            pararVideo();
            finish();
        });

        // Controle manual da SeekBar pelo usuário
        seekBarVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoViewCurso.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isTracking = false;
            }
        });
    }

    private void prepararVideo() {
        // Nome do arquivo em res/raw/video_dsm.mp4
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_dsm;
        videoViewCurso.setVideoURI(Uri.parse(videoPath));
        seekBarVideo.setProgress(0);
    }

    private void pararVideo() {
        handler.removeCallbacks(atualizarSeekBarRunnable);
        videoViewCurso.stopPlayback();
        seekBarVideo.setProgress(0);
        prepararVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(atualizarSeekBarRunnable);
    }
}
