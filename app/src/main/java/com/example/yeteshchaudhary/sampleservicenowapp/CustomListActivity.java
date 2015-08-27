package com.example.yeteshchaudhary.sampleservicenowapp;


import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class CustomListActivity extends ListActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonString= getIntent().getStringExtra("json");
        String fieldString= getIntent().getStringExtra("field");

        List<String> valsList= (new JsonParser()).getValues(jsonString,fieldString);
        String[] items = valsList.toArray(new String[valsList.size()]);

        setListAdapter(new ArrayAdapter<String>(CustomListActivity.this,R.layout.activity_custom_list, items));

    }

}