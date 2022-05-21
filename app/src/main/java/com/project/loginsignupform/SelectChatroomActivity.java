package com.project.loginsignupform;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectChatroomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_chatroom);

        // Get reference of widgets from XML layout
        ListView lv = (ListView) findViewById(R.id.lv);
        final TextView tv = (TextView) findViewById(R.id.tv);

        // Initializing a new String Array
        String[] fruits = new String[] {
                "Database Management system",
                "Embedded Systems",
                "Operating system",
                "object oriented analysis and design",
                "Economics",
                "Artificial intelligence"
        };

        // Create a List from String Array elements
        List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

        // Create a ArrayAdapter from List
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);

        // Populate ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);

        // Set an item click listener for ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                //String selectedItem = (String) parent.getItemAtPosition(position);
                if(position == 0) {
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "DBMS");
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "ES");
                    startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "OS");
                    startActivity(intent);
                }else if(position == 3){
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "OOAD");
                    startActivity(intent);
                }else if(position == 4){
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "ECO");
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SelectChatroomActivity.this, MainChatActivity.class).putExtra("string", "AI");
                    startActivity(intent);
                }
            }
        });
    }
}