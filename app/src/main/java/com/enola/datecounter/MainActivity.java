package com.enola.datecounter;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.enola.datecounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    int[] imgResources = {R.drawable.avatar,R.drawable.luffy,R.drawable.one_piece};
    int imgIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListener();
    }

    private void initListener() {
        binding.btNext.setOnClickListener(this);
        binding.btPrevious.setOnClickListener(this);
    }

    private void initUi() {
        binding.imageView.setImageResource(imgResources[imgIndex]);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btNext){
            if (imgIndex==imgResources.length -1){
                imgIndex = 0;
            }else {
                imgIndex ++;
            }
        }else {
            if (imgIndex == 0){
                imgIndex = imgResources.length -1;
            }else {
                imgIndex--;
            }
        }
        binding.imageView.setImageResource(imgResources[imgIndex]);
    }
}