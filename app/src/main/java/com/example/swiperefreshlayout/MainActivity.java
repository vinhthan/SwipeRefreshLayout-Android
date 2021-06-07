package com.example.swiperefreshlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView rcy;
    private SwipeRefreshLayout swipe;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcy = findViewById(R.id.rcy);
        swipe = findViewById(R.id.swipe);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcy.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter();
        userAdapter.setData(getListUser());
        rcy.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcy.addItemDecoration(itemDecoration);

        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.teal_200));
    }

    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new User("name "+i));
        }
        return list;

    }

    @Override
    public void onRefresh() {
        userAdapter.setData(getListUser());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        }, 2000);
    }
}