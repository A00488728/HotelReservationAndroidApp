package com.example.myapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {

    private List<GuestDetails> itemList;

    public List<GuestDetails> getGuestDetails() {
        return itemList; // Return the entire list of inputs
    }
    public MyRecyclerViewAdapter(List<GuestDetails> itemList) {
        this.itemList = itemList;

    }
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_list_layout, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        GuestDetails item = itemList.get(position);
        holder.guestName.setText(item.getName());
        holder.guestMale.setChecked(item.getMale());
        holder.guestFemale.setChecked(item.getFemale());

        holder.guestMale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setMale(isChecked);
        });
        holder.guestFemale.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setFemale(isChecked);
        });

        holder.guestName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Update the inputText in Item
                item.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }


    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder{

        EditText guestName;
        RadioButton guestMale;
        RadioButton guestFemale;
        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.enterName);
            guestMale = itemView.findViewById(R.id.radioButton1);
            guestFemale = itemView.findViewById(R.id.radioButton2);
        }
    }


}
