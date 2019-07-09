package it.univaq.mobileprogramming.pharmacy.activity.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.univaq.mobileprogramming.pharmacy.R;
import it.univaq.mobileprogramming.pharmacy.activity.DetailsActivity;
import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    private List<Pharmacy> data;

    public AdapterRecycler(List<Pharmacy> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Pharmacy pharmacy = data.get(i);
        viewHolder.title.setText(pharmacy.getDescr());
        viewHolder.subtitle.setText(pharmacy.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Use ViewHolder Pattern
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;

        ViewHolder(@NonNull View view) {
            super(view);

            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);

            // Define the click event on item
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Open another Activity and pass to it the right pharmacy
                    Pharmacy f = data.get(getAdapterPosition());


                    Pharmacy pharmacy = data.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra("name", pharmacy.getDescr());
                    intent.putExtra("address", pharmacy.getAddress());
                    intent.putExtra("Provence", pharmacy.getProv());
                    intent.putExtra("cap", pharmacy.getCap());
                    intent.putExtra("region", pharmacy.getDescrReg());
                    intent.putExtra("Cod Asl", pharmacy.getCodasl());
                    intent.putExtra("Lat",pharmacy.getLatitude());
                    intent.putExtra("Lon", pharmacy.getLongitude());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

