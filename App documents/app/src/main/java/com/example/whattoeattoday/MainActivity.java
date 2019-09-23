package com.example.whattoeattoday;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPref = getSharedPreferences("storage_file",MODE_PRIVATE);

        final TextView chosenFood = findViewById(R.id.mainFoodTxtV);


        final Button EditFoodbutton = findViewById(R.id.mainEditFoodListBtn);
        EditFoodbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity.class );
                startActivity(intent);
            }
        });



        final Button DecideButton = findViewById(R.id.mainDecideBtnn);
        DecideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String choice = "No foods available";
                String string = sharedPref.getString("chosenFood", choice);


                if (string.equals(choice)){}else{
                    String[] allFoods = string.split(",");
                    List<String> FoodList = new ArrayList<String>();
                    FoodList = Arrays.asList(allFoods);
                    choice = getChoice(FoodList);
                }
                String upperString = choice.substring(0,1).toUpperCase() + choice.substring(1);
                chosenFood.setText(upperString);
            }
        });
    }

    public String getChoice(List<String> FoodList){
        int x = (int)(Math.random()*((FoodList.size())));
        return FoodList.get(x);
    }




}
