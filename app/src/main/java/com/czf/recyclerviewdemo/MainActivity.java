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
    RecyclerView recyclerView = findViewById(R.id.recycler_view);

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
    recyclerView.setLayoutManager(new FormLayoutManager(adapter.getColumnCount(), recyclerView));
    RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
    pool.setMaxRecycledViews(0, 100);
    recyclerView.setRecycledViewPool(pool);
  }

  public static class MyAdapter extends BaseFormAdapter<List<String>> {

    public MyAdapter(Context context, List<List<String>> data) {
      super(context, data);
    }

    @Override
    public int getItemViewType(int position) {
      return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder createViewHolder(@NonNull View view, int viewType) {
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      int column = getColumnCount();
      String str = mList.get(position / column).get(position % column);
      ((MyViewHolder)holder).text.setText(str);
      Log.d("--==--", "bindVH: " + position);
    }

    @Override
    public int getRowCount() {
      return mList != null ? mList.size() : 0;
    }

    @Override
    public int getColumnCount() {
      return (mList != null && mList.size() > 0) ? mList.get(0).size() : 0;
    }

    @Override
    protected View createView(ViewGroup viewGroup, int viewType) {
      Log.d("--==--", "createView");
      return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, null);
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
