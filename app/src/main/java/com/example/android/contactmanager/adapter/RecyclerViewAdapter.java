package com.example.android.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.contactmanager.DisplayContact;
import com.example.android.contactmanager.MainActivity;
import com.example.android.contactmanager.R;
import com.example.android.contactmanager.model.Contact;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView imageIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            contactName = itemView.findViewById(R.id.contact_name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            imageIcon = itemView.findViewById(R.id.contact_icon);

            imageIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Contact contact = contactList.get(position);
            String name = contact.getName();
            String phone = contact.getPhoneNumber();

            Log.d("sachindb","clicked");
            Toast.makeText(context,"The position is "+ getPosition(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, DisplayContact.class);
            intent.putExtra("Rname",name);
            intent.putExtra("Rphone",phone);
            context.startActivity(intent);
        }
    }
}
