package com.enola.datecounter;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.enola.datecounter.databinding.ActivityDateCounterBinding;
import com.enola.datecounter.databinding.DialogUserNameInputBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class date_counter_activity extends AppCompatActivity {
    private static final String SELECTED_DATE = "selected_date";
    private ActivityDateCounterBinding binding;
    private LocalDate selectedDate;

    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDateCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initSharePreference();
        initUi();
        initListener();
    }

    private void initSharePreference() {
        sp = getSharedPreferences("date_counter",MODE_PRIVATE);
    }


    private void initListener() {
//        binding.btDate.setOnClickListener(v -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            AlertDialog dialog = builder
//                    .setIcon(R.drawable.ic_male)
//                    .setTitle("Alert Dialog")
//                    .setMessage("This is alert dialog")
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    })
//                    .create();
//            dialog.show();
//        });
        binding.tvMale.setOnClickListener(v-> {
            showAlertDialog(v);
        });
        binding.tvFemale.setOnClickListener(v->{
            showAlertDialog(v);
        });
        binding.btDate.setOnClickListener(v -> {
            showDatePickerDialog();
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.updateDate(selectedDate.getYear(),selectedDate.getMonthValue() -1, selectedDate.getDayOfMonth());
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            //in date picker, describe month with index number eg. May -> 4
            selectedDate = LocalDate.of(year,month + 1,dayOfMonth);
            saveData(SELECTED_DATE,String.format("%02d/%02d/%04d",dayOfMonth, month +1 , year));
            updateDate();
        });
        datePickerDialog.show(); //don't forget to call show()
    }

    private void showAlertDialog(View view){

        View view1 = new TextView(this); // child to parent -> upcasting(implicit)
        TextView textView = (TextView) view; //downcasting (parent to child) implicit

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        DialogUserNameInputBinding dialogBinding = DialogUserNameInputBinding.inflate(getLayoutInflater());
        AlertDialog alertDialog = alertBuilder.setView(dialogBinding.getRoot()).create();

        alertDialog.show();  //if you don't use show(), alert dialog box will not show on display

        class OnDialogClick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                var name = dialogBinding.etName.getText().toString();
                if(textView.getId() == R.id.tv_male){
                    saveData(MALE,name);
                }else {
                    saveData(FEMALE,name);

                }
                textView.setText(name);
                alertDialog.cancel();
            }
        }
        dialogBinding.btCancel.setOnClickListener(v -> alertDialog.cancel());
        dialogBinding.btConfirm.setOnClickListener( new OnDialogClick());
    }

    private void saveData(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        //editor.commit();//return boolean value
        editor.apply(); //use apply(), u don't care return value

    }

    private String read(String key){
        return sp.getString(key,"");
    }
    private String readDate(){
        return sp.getString(SELECTED_DATE, "01/06/2021");
    }

    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private void initUi() {

        binding.tvMale.setText(read(MALE));
        binding.tvFemale.setText(read(FEMALE));
        selectedDate = fromStringToLocalDate(readDate());
        updateDate();
    }

    private void updateDate() {
        LocalDate todayDate = LocalDate.now();
        long dateBetween = todayDate.toEpochDay() - selectedDate.toEpochDay();
        String days = dateBetween > 1 ? dateBetween + "Days" : dateBetween + "Day";
        binding.tvDateBetween.setText(days);
        binding.btDate.setText(fromLocalDatetoString(selectedDate));
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String fromLocalDatetoString(LocalDate localDate){
        return formatter.format(localDate);
    }

    private LocalDate fromStringToLocalDate(String str){
        return LocalDate.parse(str, formatter);
    }

}