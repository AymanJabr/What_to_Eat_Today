package com.example.whattoeattoday;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends ArrayAdapter<Food> {

    private List<Food> items;
    private int layoutResourceId;
    private Context context;

    public FoodListAdapter(Context context, int layoutResourceId, List<Food> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    //public FoodListAdapter(Context context, ArrayList<Food> foods){ super(context,0, foods);}


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        FoodHolder holder = null;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.foodlayout, parent, false);
        }


        holder = new FoodHolder();
        holder.food = items.get(position);



        //Food currentFood = getItem(position);

        TextView foodTextView = listItemView.findViewById(R.id.foodName);
        foodTextView.setText(holder.food.getFoodName());

        listItemView.setTag(holder);


        //Button deleateBtn = listItemView.findViewById(R.id.deleteButton);
        //deleateBtn.setTag(currentFood);

//        deleateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int position = (Integer) v.getId();
//
//                Food food = getItem(position);
//
//
//                //food.d
//                notifyDataSetChanged();
//            }
//        });



        return listItemView;
    }



    public static class FoodHolder {
        Food food;
        TextView name;
        ImageButton removeFoodButton;
    }



}
