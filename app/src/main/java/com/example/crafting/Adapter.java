package com.example.crafting;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.sharmin.charging.AdsLib;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Data>DataList;
    Context mcontext;
    AdsLib adsLib;

    public Adapter(List<Data> dataList, Context mcontext) {
        DataList = dataList;
        this.mcontext = mcontext;

    }

    public void setDataList(List<Data> dataList) {
        DataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.cardviewitemsingle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data data=DataList.get(position);
        holder.title.setText(data.getTitle());
        try {
            // get input stream
            InputStream ims =mcontext.getAssets().open(data.getImage());

            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.imageView.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
        holder.details.setText(data.getDesc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String uri;
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.housebeautiful.com/home-remodeling/diy-projects/how-to/g1624/diy-solutions-easier-life/"));
                    mcontext.startActivity(intent);



//                Intent intent=new Intent(mcontext,DetailsActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("uri",data.getUri());
//                intent.putExtra("title",data.getTitle());
//                intent.putExtra("image",data.getImage());
//                intent.putExtra("desc",data.getDesc());
//                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title,details;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card);
            title=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image);
            details=itemView.findViewById(R.id.detailsDetailCard);
        }
    }

}
