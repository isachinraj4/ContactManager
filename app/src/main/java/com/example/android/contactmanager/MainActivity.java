package com.example.android.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.contactmanager.adapter.RecyclerViewAdapter;
import com.example.android.contactmanager.data.MyDbHandler;
import com.example.android.contactmanager.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        //Initializing recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDbHandler db = new MyDbHandler(MainActivity.this);

        contactArrayList = new ArrayList<>();

        //Getting all contacts
        List<Contact> allContacts = db.getAllContacts();
        for(Contact contact: allContacts){
            Log.d("sachindb","\nID: " +contact.getId()+"\n"+"Name: "+contact.getName()+"\n"+
                            "Phone Number: "+contact.getPhoneNumber()+"\n");

        contactArrayList.add(contact);
        }
        // Using RecyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    private void addListenerOnButton(){
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.bringToFront();
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AddContact.class);
            startActivity(intent);
        });
    }
}