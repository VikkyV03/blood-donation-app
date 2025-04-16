package com.example.bloodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bloodapp.R;
import com.example.bloodapp.models.BloodBank;

import java.util.List;

public class BloodBankAdapter extends RecyclerView.Adapter<BloodBankAdapter.ViewHolder> {

    private List<BloodBank> bloodBankList; // Removed final

    public BloodBankAdapter(List<BloodBank> bloodBankList) {
        this.bloodBankList = bloodBankList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, city, contact;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.bank_name);
            address = view.findViewById(R.id.bank_address);
            city = view.findViewById(R.id.bank_city);
            contact = view.findViewById(R.id.bank_contact);
        }

        public void bind(BloodBank bank) {
            name.setText(bank.getName());
            address.setText(bank.getAddress());
            city.setText(bank.getCity());
            contact.setText(bank.getContact());
        }
    }

    @Override
    public BloodBankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blood_bank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(bloodBankList.get(position));
    }

    @Override
    public int getItemCount() {
        return bloodBankList.size();
    }

    // 🔁 Method to update the data list dynamically
    public void updateData(List<BloodBank> newList) {
        this.bloodBankList = newList;
        notifyDataSetChanged();
    }
}
