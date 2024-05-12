package com.enola.datecounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {
    int[] imgResources = {R.drawable.childhood,R.drawable.lucy,R.drawable.growup};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

    }

    public void cardItemClicked(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
//         int id = view.getId();
//        if(id==R.id.cvChildhood){
//            System.out.println("Luffy Childhood");
//        } else if (id==R.id.cvGearFifth) {
//            System.out.println("Gear 5");
//        }else {
//            System.out.println("Luffy's grown up");
//        }
//
//    }
    }

}