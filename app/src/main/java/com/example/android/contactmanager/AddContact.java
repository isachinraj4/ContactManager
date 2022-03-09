package com.example.android.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.contactmanager.data.MyDbHandler;
import com.example.android.contactmanager.model.Contact;

public class AddContact extends AppCompatActivity {
    EditText name , number;
    Button addContact;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        //Creating object of MyDbHandler
        MyDbHandler dbHandler = new MyDbHandler(AddContact.this);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        // creating to add contact
        name  = (EditText) findViewById(R.id.personName);
        number  = (EditText) findViewById(R.id.phoneNumber);
        addContact = (Button) findViewById(R.id.add_contact);
        addContact.setOnClickListener(v -> {
            Contact contact = new Contact();
            contact.setName(name.getText().toString());
            contact.setPhoneNumber(number.getText().toString());
            dbHandler.addContact(contact);
            Intent intent = new Intent(AddContact.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Contact Added Successfully",Toast.LENGTH_SHORT).show();
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
