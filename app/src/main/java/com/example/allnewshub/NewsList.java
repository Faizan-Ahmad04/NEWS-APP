package com.example.allnewshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NewsList extends AppCompatActivity implements CoursesRecyclerViewAdapter.ItemClickListenerInterface{
    CoursesRecyclerViewAdapter coursesRecyclerViewAdapter;
    ArrayList<String> titleList;
    ArrayList<String> linkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        titleList = (ArrayList<String>) getIntent().getSerializableExtra("data");
        linkList =(ArrayList<String>) getIntent().getSerializableExtra("link");

        RecyclerView mRecyclerView = (RecyclerView)  findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        coursesRecyclerViewAdapter = new CoursesRecyclerViewAdapter(this, titleList, this);
        mRecyclerView.setAdapter(coursesRecyclerViewAdapter);
    }

//    @Override
//    public void onItemClicked(View view, int position) {
//        Snackbar.make(view,"Clicked" + coursesRecyclerViewAdapter.getItem(position),Snackbar.LENGTH_LONG).show();
//    }
    Intent intent = getIntent();

    public void onItemClicked(View view, int position) {
        //call webview activity
        Intent intent = new Intent(NewsList.this,NewsWebViewActivity.class);
        intent.putExtra("url", linkList.get(position));
        startActivity(intent);
    }
}