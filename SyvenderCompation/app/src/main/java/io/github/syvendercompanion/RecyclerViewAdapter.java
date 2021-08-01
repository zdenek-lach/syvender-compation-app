package io.github.syvendercompanion;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context myContext;
    private List<Item> myData;

    public RecyclerViewAdapter(Context myContext, List<Item> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater myInflater = LayoutInflater.from(myContext);
        view = myInflater.inflate(R.layout.cardview_item_collection, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_item_title.setText(myData.get(position).getTitle());
        holder.img_item_thumbnail.setImageResource(myData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, ItemActivity.class);


                intent.putExtra("Title", myData.get(position).getTitle());
                intent.putExtra("Description", myData.get(position).getDescription());
                intent.putExtra("Category", myData.get(position).getCategory());
                intent.putExtra("Thumbnail", myData.get(position).getThumbnail());



                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_item_title;
        ImageView img_item_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_item_title = (TextView) itemView.findViewById(R.id.item_collection_item_title_id);
            img_item_thumbnail = (ImageView) itemView.findViewById(R.id.item_collection_item_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}
