package com.elgohry.ibrahimhany.gittest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elgohry.ibrahimhany.gittest.Adapters.SubAdapter;
import com.elgohry.ibrahimhany.gittest.ApiService.ApiService;
import com.elgohry.ibrahimhany.gittest.Model.SubInfo;
import com.elgohry.ibrahimhany.gittest.Transformation.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
    ImageView subImage;
    TextView subName,subCount;
    RecyclerView subList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        subCount=findViewById(R.id.Count);
        subImage=findViewById(R.id.subImage);
        subList=findViewById(R.id.subList);
        subName=findViewById(R.id.subName);
        subList.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        subList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        int id = Integer.parseInt(getIntent().getExtras().get("id").toString());
        String avatar=getIntent().getExtras().get("avatar").toString();
        final String name=getIntent().getExtras().get("name").toString();
        String subUrl=getIntent().getExtras().get("subUrl").toString();
        Picasso.with(DetailsActivity.this).load(avatar).transform(new CircleTransform()).into(subImage);


        subName.setText(name);
        // this url should be dynamic
      String base="https://api.github.com/";
      String host=subUrl.substring(base.length(),subUrl.length());

        new Retrofit.Builder().baseUrl(base)

                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getSubscribers(host)
                .enqueue(new Callback<List<SubInfo>>() {
                    @Override
                    public void onResponse(Call<List<SubInfo>> call, Response<List<SubInfo>> response) {
                        List<SubInfo> subInfos=response.body();
                        subCount.setText(String.valueOf(subInfos.size()));
                        SubAdapter adapter=new SubAdapter(DetailsActivity.this,subInfos);
                        subList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<SubInfo>> call, Throwable t) {
                        Toast.makeText(DetailsActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });






    }
}
