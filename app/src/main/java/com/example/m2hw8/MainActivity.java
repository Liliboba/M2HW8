package com.example.m2hw8;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m2hw8.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btr.setOnClickListener(v -> {

            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i <= 100; i++) {
                        int percent = i;

                        runOnUiThread(() -> {
                            binding.textView.setText(percent + " %");
                            binding.progress.setProgress(percent, true);
                        });

                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw  new RuntimeException(e);
                        }
                    }
                    runOnUiThread(() -> {
                        binding.lottie.setVisibility(View.VISIBLE);
                        binding.lottie.playAnimation();
                    });
                }
            };
            thread.start();
        });
    }
}