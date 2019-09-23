package com.example.whattoeattoday;

import java.io.Serializable;


//Defines a Food class that takes that has only one parameter @param mFoodName string attached to it,
// and has a getter and setter methods. as well as a constructor.
public class Food implements Serializable {

    private String mFoodName;

    public Food(String FoodName){
        this.setmFoodName(FoodName);
    };

    public String getFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }
}
