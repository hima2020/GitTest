package com.elgohry.ibrahimhany.gittest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.elgohry.ibrahimhany.gittest.Adapters.RecyclerAdapter;
import com.elgohry.ibrahimhany.gittest.ApiService.ApiManager;
import com.elgohry.ibrahimhany.gittest.ApiService.ApiService;
import com.elgohry.ibrahimhany.gittest.Model.ItemsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ItemsItem> items=new ArrayList<>();
    RecyclerAdapter adapter;
    LinearLayout noData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        noData=findViewById(R.id.noData);

    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do your action
                ApiManager manager=new ApiManager();
                Retrofit retrofit=manager.CreateRetrofit();
                ApiService service=retrofit.create(ApiService.class);
                if (query.length()>3){
                    service.getData(query).enqueue(new Callback<com.elgohry.ibrahimhany.gittest.Model.Response>() {
                        @Override
                        public void onResponse(Call<com.elgohry.ibrahimhany.gittest.Model.Response> call, Response<com.elgohry.ibrahimhany.gittest.Model.Response> response) {

                            items=response.body().getItems();
                            adapter=new RecyclerAdapter(MainActivity.this,items);

                            if (response.isSuccessful()){

                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                noData.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else noData.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<com.elgohry.ibrahimhany.gittest.Model.Response> call, Throwable t) {

                        }


                    });
                }
                else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    noData.setVisibility(View.VISIBLE);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {


                ApiManager manager=new ApiManager();
                Retrofit retrofit=manager.CreateRetrofit();
                ApiService service=retrofit.create(ApiService.class);
                if (newText.length()>3){
                    service.getData(newText).enqueue(new Callback<com.elgohry.ibrahimhany.gittest.Model.Response>() {
                        @Override
                        public void onResponse(Call<com.elgohry.ibrahimhany.gittest.Model.Response> call, Response<com.elgohry.ibrahimhany.gittest.Model.Response> response) {

                            items=response.body().getItems();
                            adapter=new RecyclerAdapter(MainActivity.this,items);

                            if (response.isSuccessful()){

                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                noData.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            else noData.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<com.elgohry.ibrahimhany.gittest.Model.Response> call, Throwable t) {

                        }


                    });
                }
                else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    noData.setVisibility(View.VISIBLE);
                }


                return false;
            }
        });

        return true;
    }

}
