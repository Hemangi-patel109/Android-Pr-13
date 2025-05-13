package com.example.myapplication13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ListView listView;
    Map<String, List<String>> data = new HashMap<>(); // Store data for each category

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);


        // Initialize data (you can fetch this from a database or API)
        data.put("Vegetables", new ArrayList<String>() {{
            add("Carrot");
            add("Broccoli");
            add("Potato");
        }});
        data.put("Fruits", new ArrayList<String>() {{
            add("Mango");
            add("Apple");
            add("Banana");
        }});
        data.put("Cars", new ArrayList<String>() {{
            add("Toyota");
            add("Honda");
            add("Ford");
        }});


        // Spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(data.keySet())); // use keyset
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString(); // Get selected item

                // Listview adapter
                List<String> itemList = data.get(selectedCategory);  // Get the list based on the selected category
                if (itemList == null) {
                    itemList = new ArrayList<>(); // Handle case where no data is found. Prevents crash.
                    Toast.makeText(MainActivity.this, "No items found for " + selectedCategory, Toast.LENGTH_SHORT).show();
                }
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, itemList);
                listView.setAdapter(listAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected (optional)
            }
        });
    }
}
