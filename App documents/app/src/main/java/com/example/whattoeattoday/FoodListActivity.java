package com.example.whattoeattoday;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//An activity that shows the list of foods available, and is used to add or delete from that list
public class FoodListActivity extends AppCompatActivity {

    //FoodList contains the actual foods as Food objects.
    final ArrayList<Food> FoodList = new ArrayList<Food>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //Makes a shared preferences editor in order to store the list of foods as one long string
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.storage_file), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        //gets the string that contains all the list of foods
        String longName = sharedPref.getString(getString(R.string.chosenFood),"");

        //Calls the getFoodList function on the longName string,
        //      THIS IS CURRENTLY IGNORED AND USELESS IN THE REST OF THE PROGRAM
        List<String> stringList = getFoodList(longName);

        //These 5 foods are added by default,   CHANGE THIS SO THAT THEY ARE ADDED ONLY WHEN THE SAVED LONGNAME HAS AN EMPTY VALUE
        FoodList.add(new Food("Pasta Spaghetti"));
        FoodList.add(new Food("Fried Potatoes"));
        FoodList.add(new Food("Pizza Margherita"));
        FoodList.add(new Food("Makloubah"));
        FoodList.add(new Food("Steak"));


        //Makes a FoodListAdapter and passes the foodlayout and the FoodList and the activity context as the parameters,
        //      and then sets the list view adapter to be that foodAdapter
        final FoodListAdapter foodAdapter = new FoodListAdapter(this,R.layout.foodlayout,FoodList);
        final ListView listView = (ListView) findViewById(R.id.foodListView);
        listView.setAdapter(foodAdapter);

        //LARGELY INGORED, AND WE MIGHT EVEN NEED TO DELETE IT
        editor.putString(getString(R.string.chosenFood),createListString());
        editor.apply();


        //Button is used to add a typed food to the list of foods
        final Button addFoodButton = findViewById(R.id.addFoodBtn);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Gets the text that the user typed in.
                EditText newFoodText = (EditText) findViewById(R.id.addFoodETxt);
                String newFood = newFoodText.getText().toString();

                //Used to check if the food that the user entered is valid
                boolean legitFood = true;

                //checks to see if entered food is empty, and gives the user a toast if it is
                if (newFood.equals("")  || newFood.equals(" ") || newFood.equals("  ") || newFood.equals("   ") ){
                    Toast toast = Toast.makeText(getApplicationContext(), "No food entered", Toast.LENGTH_LONG);
                    toast.show();
                    legitFood = false;
                } else {
                    for (Food food : FoodList) {
                        if (food.getFoodName().equals(newFood) ){
                            legitFood = false;
                        }
                    }

                    //If the food is valid it is then set at the first value of the FoodList
                    if (legitFood) {
                        FoodList.add(0, new Food(newFood));
                        listView.setAdapter(foodAdapter);


                        //TO BE RECHECKED, MIGHT NOT BE DOING ANYTHING CURRENTLY
                        foodAdapter.notifyDataSetChanged();
                        editor.putString(getString(R.string.chosenFood), createListString());
                        editor.apply();

                    } else {//If the food is already in the FoodList, the it displays a toast to the user
                        Toast toast = Toast.makeText(getApplicationContext(), "Food already exists", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                //After the user adds the food, the EditText field is reset to empty
                newFoodText.setText("");
            }
        });



    }

    //MIGHT NOT BE CURRENTLY USED
    //It is supposed to build and return a string from a given list of Food items
    public String createListString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Food s: FoodList) {
            stringBuilder.append(s.getFoodName());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }


    //A function that uses REGEX to split a given string
    //      wherever there is a "," and return the result as a list of string
    public List<String> getFoodList(String string){

        if(string.equals("")){return null;}else {
            String[] allFoods = string.split(",");
            List<String> FoodList = new ArrayList<String>();
            FoodList = Arrays.asList(allFoods);

            return FoodList;
        }
    }
}
