package com.elgohry.ibrahimhany.gittest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.elgohry.ibrahimhany.gittest.DetailsActivity;
import com.elgohry.ibrahimhany.gittest.Model.ItemsItem;
import com.elgohry.ibrahimhany.gittest.R;
import com.elgohry.ibrahimhany.gittest.Transformation.CircleTransform;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<ItemsItem> items=new ArrayList<>();

    public RecyclerAdapter(Context context, List<ItemsItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      ItemsItem item=items.get(i);
      viewHolder.repo_name.setText(item.getName());
if (item.getDescription()!=null){
    viewHolder.repo_desc.setText("No Description");
    if (item.getDescription().equals("")){
        viewHolder.repo_desc.setText("No Description ");
    }
    else {
        viewHolder.repo_desc.setText(item.getDescription());
    }
}

      viewHolder.repo_forks.setText(String.valueOf(item.getForksCount()));
        Picasso.with(context).load(item.getOwner().getAvatarUrl())
                .transform(new CircleTransform())
                .into(viewHolder.avatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView repo_name,repo_desc,repo_forks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar=itemView.findViewById(R.id.avatar);
            repo_name=itemView.findViewById(R.id.repo_name);
            repo_desc=itemView.findViewById(R.id.repo_desc);
            repo_forks=itemView.findViewById(R.id.repo_forks);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = getAdapterPosition();
            final Intent intent=new Intent(context, DetailsActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("avatar",items.get(id).getOwner().getAvatarUrl());
            intent.putExtra("name",items.get(id).getName());
            intent.putExtra("subUrl",items.get(id).getSubscribersUrl());
            context.startActivity(intent);



        }
    }
}
