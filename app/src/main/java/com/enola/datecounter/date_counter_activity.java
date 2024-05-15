package com.enola.datecounter;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.enola.datecounter.databinding.ActivityDateCounterBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class date_counter_activity extends AppCompatActivity {
    private ActivityDateCounterBinding binding;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListener();
    }

    private void initListener() {
        binding.btDate.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder
                    .setIcon(R.drawable.ic_male)
                    .setTitle("Alert Dialog")
                    .setMessage("This is alert dialog")
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create();
            dialog.show();
        });



    }

    private void initUi() {
        selectedDate = fromStringToLocalDate(binding.btDate.getText().toString());
        LocalDate todayDate = LocalDate.now();
        long dateBetween = todayDate.toEpochDay() - selectedDate.toEpochDay();
        String days = dateBetween > 1 ? dateBetween + "Days" : dateBetween + "Day";
        binding.tvDateBetween.setText(days);
    }
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String fromLocalDatetoString(LocalDate localDate){
        return formatter.format(localDate);
    }

    private LocalDate fromStringToLocalDate(String str){
        return LocalDate.parse(str, formatter);
    }

}