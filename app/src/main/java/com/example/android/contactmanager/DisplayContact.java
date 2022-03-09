package com.example.android.contactmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contactmanager.data.MyDbHandler;

public class DisplayContact extends AppCompatActivity {

    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        //Creating object of MyDbHandler
        MyDbHandler dbHandler = new MyDbHandler(DisplayContact.this);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Rname");
        String phone = intent.getStringExtra("Rphone");

        TextView nameTextView = findViewById(R.id.display_name);
        nameTextView.setText(name);

        TextView phoneTextView = findViewById(R.id.display_phone);
        phoneTextView.setText(phone);

        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            dbHandler.deleteContact(name);
            Intent intent1 = new Intent(DisplayContact.this,MainActivity.class);
            startActivity(intent1);
            Toast.makeText(getApplicationContext(),"Contact Deleted Successfully",Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}