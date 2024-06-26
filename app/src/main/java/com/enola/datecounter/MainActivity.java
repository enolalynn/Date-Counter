package com.enola.datecounter;

import android.os.Bundle;
import android.os.Handler;
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
    String[] names = {"Avatar","Luffy","Luffy's Smile"};

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
        binding.piLoading.setVisibility(View.VISIBLE);
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
//        try {
//            Thread.sleep(1000);
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.piLoading.setVisibility(View.INVISIBLE);
                binding.imageView.setImageResource(imgResources[imgIndex]);
                binding.tvName.setText(names[imgIndex]);
            }
        },2000);

    }
}