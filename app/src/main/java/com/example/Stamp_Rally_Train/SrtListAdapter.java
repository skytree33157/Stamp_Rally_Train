package com.example.Stamp_Rally_Train;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SrtListAdapter extends RecyclerView.Adapter<SrtListAdapter.TripViewHolder> {

    private final List<Srt> tripList;
    private final OnTripDeleteListener deleteListener;

    public interface OnTripDeleteListener {
        void onTripDelete(Srt trip);
    }

    public SrtListAdapter(List<Srt> tripList, OnTripDeleteListener listener) {
        this.tripList = tripList;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_info, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Srt currentTrip = tripList.get(position);

        holder.tvTripMain.setText(currentTrip.toString());
        holder.tvTripDetails.setText(currentTrip.getRouteString());

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onTripDelete(currentTrip);
            }
        });
    }
    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public void removeItem(Srt trip) {
        int position = tripList.indexOf(trip);
        if (position > -1) {
            tripList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tripList.size());
        }
    }

    static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView tvTripMain;
        TextView tvTripDetails;
        Button btnDelete;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTripMain = itemView.findViewById(R.id.tv_trip_main);
            tvTripDetails = itemView.findViewById(R.id.tv_trip_details);
            btnDelete = itemView.findViewById(R.id.btn_delete_trip);
        }
    }
}