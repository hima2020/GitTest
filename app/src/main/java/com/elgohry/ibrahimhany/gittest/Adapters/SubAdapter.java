package com.elgohry.ibrahimhany.gittest.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elgohry.ibrahimhany.gittest.Model.ItemsItem;
import com.elgohry.ibrahimhany.gittest.Model.SubInfo;
import com.elgohry.ibrahimhany.gittest.R;
import com.elgohry.ibrahimhany.gittest.Transformation.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {
    Context context;
    List<SubInfo> subItems;

    public SubAdapter(Context context, List<SubInfo> subItems) {
        this.context = context;
        this.subItems = subItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.sub_item,viewGroup,false);
       ViewHolder holder=new ViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SubInfo item=subItems.get(i);
        viewHolder.subListName.setText(item.getLogin());
        Picasso.with(context).load(item.getAvatarUrl())
                .transform(new CircleTransform())
                .into(viewHolder.subListImage);

    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView subListImage;
        TextView subListName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subListImage=itemView.findViewById(R.id.subListImage);
            subListName=itemView.findViewById(R.id.subListName);

        }
    }
}
