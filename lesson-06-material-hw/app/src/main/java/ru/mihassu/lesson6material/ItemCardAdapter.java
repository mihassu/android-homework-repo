package ru.mihassu.lesson6material;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemCardAdapter extends RecyclerView.Adapter<ItemCardAdapter.ViewHolder> {

    private List<ItemCardModel> itemsList;

    public ItemCardAdapter(List<ItemCardModel> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemCardModel current = itemsList.get(position);
        holder.itemThumbnail.setImageResource(current.getImageID());
        holder.itemTitle.setText(current.getTitle());
        holder.itemDescription.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemThumbnail;
        public TextView itemTitle;
        public TextView itemDescription;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.itemThumbnail = view.findViewById(R.id.item_thumbnail);
            this.itemTitle = view.findViewById(R.id.item_title);
            this.itemDescription = view.findViewById(R.id.item_description);

        }
    }
}
