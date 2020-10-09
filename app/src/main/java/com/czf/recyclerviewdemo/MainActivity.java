package com.czf.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);

    List<String> list = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      list.add("list data: " + i);
    }

    recyclerView.setAdapter(new MyAdapter(list));
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
  }

  public static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<String> data;

    public MyAdapter(List<String> data) {
      this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
      return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      TextView tv = new TextView(parent.getContext());
      return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      TextView tv = (TextView)holder.itemView;
      float density = tv.getResources().getDisplayMetrics().density;
      tv.setHeight(Math.round(50 * density));
      tv.setPadding(Math.round(10 * density), 0, Math.round(10 * density), 0);
      tv.setGravity(Gravity.CENTER);
      ((TextView)holder.itemView).setText(this.data.get(position));
    }

    @Override
    public int getItemCount() {
      return data.size();
    }
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(View itemView) {
      super(itemView);
    }

  }

}
