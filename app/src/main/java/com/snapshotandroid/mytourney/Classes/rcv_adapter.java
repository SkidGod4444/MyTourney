//package com.snapshotandroid.mytourney.Classes;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.snapshotandroid.mytourney.R;
//
//import java.util.List;
//
//public class rcv_adapter extends RecyclerView.Adapter<rcv_adapter.rcvViewHolder> {
//
//    List<Tourney> tourneyList;
//
//    public rcv_adapter(List<Tourney> tourneyList) {
//        this.tourneyList = tourneyList;
//    }
//
//    @NonNull
//    @Override
//    public rcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_item_layout, parent, false);
//        return new rcvViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull rcvViewHolder holder, int position) {
//        Tourney tourney = tourneyList.get(position);
//
//        holder.ttitle.setText(tourney.getTitle());
//        holder.tdatentime.setText(tourney.getDateAndTime());
//        holder.torganizer.setText(tourney.getOrganizer());
//        // You might want to load the image here, e.g., using Glide or Picasso
//        // Example: Glide.with(holder.itemView.getContext()).load(tourney.getImageUrl()).into(holder.timage);
//    }
//
//    @Override
//    public int getItemCount() {
//        return tourneyList.size();
//    }
//
//    class rcvViewHolder extends RecyclerView.ViewHolder {
//
//        TextView ttitle, tdatentime, torganizer;
//        ImageView timage;
//
//        public rcvViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            ttitle = itemView.findViewById(R.id.rcv_ttitle);
//            tdatentime = itemView.findViewById(R.id.rcv_tdatentime);
//            torganizer = itemView.findViewById(R.id.rcv_torganizer);
//            timage = itemView.findViewById(R.id.rcv_timage);
//        }
//    }
//}
