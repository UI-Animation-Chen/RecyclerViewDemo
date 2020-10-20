package com.czf.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dnwalter.formlayoutmanager.adapter.BaseFormAdapter;
import com.dnwalter.formlayoutmanager.layoutmanager.FormLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new MTTableView(this.getApplicationContext()));
    setContentView(R.layout.activity_main);

    FrameLayout container = findViewById(R.id.container);
    RecyclerView recyclerView =
            (RecyclerView) LayoutInflater
                    .from(getApplicationContext())
                    .inflate(R.layout.androidx_recyclerview, null, false);
    container.addView(recyclerView);
    recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

    List<List<String>> list = new ArrayList<>(100);
    for (int row = 0; row < 100; row++) {
      List<String> temp = new ArrayList<>(100);
      for (int column = 0; column < 100; column++) {
        temp.add(row + ", " + column);
      }
      list.add(temp);
    }

    MyAdapter adapter = new MyAdapter(this, list);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new FixedGridLayoutManager(adapter.getColumnCount()));
    RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
    pool.setMaxRecycledViews(0, 50);
    recyclerView.setRecycledViewPool(pool);
  }

  public static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private int count;
    private List<List<String>> mList;

    public MyAdapter(Context context, List<List<String>> data) {
      mList = data;
    }

    @Override
    public int getItemViewType(int position) {
      return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null);
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      int column = getColumnCount();
      final String str = mList.get(position / column).get(position % column);
      final TextView tv = ((MyViewHolder)holder).text;
      tv.setText(str);
      tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          count++;
          tv.setText(str + count);
          notifyDataSetChanged();
        }
      });
      Log.d("--==--", "bindVH: " + position);
    }

    @Override
    public int getItemCount() {
      return getRowCount() * getColumnCount();
    }

    public int getRowCount() {
      return mList != null ? mList.size() : 0;
    }

    public int getColumnCount() {
      return (mList != null && mList.size() > 0) ? mList.get(0).size() : 0;
    }
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView text;

    public MyViewHolder(View itemView) {
      super(itemView);
      text = itemView.findViewById(R.id.item_text);
    }

  }

}
