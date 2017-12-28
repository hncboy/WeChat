package com.example.hnc.wechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.bean.User;

import java.util.List;

/**
 * Created by pc on 2017/12/27.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<User> contactsList;

    static class ContactsViewHolder extends RecyclerView.ViewHolder {

        public View contactsView;
        public ImageView contactsPhoto;
        public TextView contactsName;

        public ContactsViewHolder(View view) {
            super(view);
            contactsView = view;
            contactsPhoto = view.findViewById(R.id.contacts_photo);
            contactsName = view.findViewById(R.id.contacts_name);
        }
    }

    public ContactsAdapter(List<User> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        final ContactsViewHolder holder = new ContactsViewHolder(view);
        holder.contactsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                User user = contactsList.get(position);
                Toast.makeText(view.getContext(), user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        User user = contactsList.get(position);
        holder.contactsPhoto.setImageResource(user.getPhotoId());
        holder.contactsName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
